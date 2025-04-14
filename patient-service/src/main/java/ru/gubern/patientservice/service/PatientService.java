package ru.gubern.patientservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gubern.patientservice.dto.PatientRequestDTO;
import ru.gubern.patientservice.dto.PatientResponseDTO;
import ru.gubern.patientservice.mapper.PatientMapper;
import ru.gubern.patientservice.model.Patient;
import ru.gubern.patientservice.repository.PatientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDto).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientDto) {
        Patient patient = patientRepository.save(PatientMapper.toPatient(patientDto));
        return PatientMapper.toDto(patient);
    }
}
