package ru.gubern.patientservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gubern.patientservice.dto.PatientRequestDTO;
import ru.gubern.patientservice.dto.PatientResponseDTO;
import ru.gubern.patientservice.exception.EmailAlreadyExistsException;
import ru.gubern.patientservice.exception.PatientNotFoundException;
import ru.gubern.patientservice.mapper.PatientMapper;
import ru.gubern.patientservice.model.Patient;
import ru.gubern.patientservice.repository.PatientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDto).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientDto) {
        if (patientRepository.existsByEmail(patientDto.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " +
                    patientDto.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toPatient(patientDto));
        return PatientMapper.toDto(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " +
                    patientRequestDto.getEmail());
        }

        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
