package ru.gubern.patientservice.mapper;

import ru.gubern.patientservice.dto.PatientRequestDTO;
import ru.gubern.patientservice.dto.PatientResponseDTO;
import ru.gubern.patientservice.model.Patient;

import java.time.LocalDate;


public class PatientMapper {

    public static PatientResponseDTO toDto(Patient patient) {
        PatientResponseDTO patientDto = new PatientResponseDTO();
        patientDto.setId(patient.getId().toString());
        patientDto.setName(patient.getName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setAddress(patient.getAddress());
        patientDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDto;
    }

    public static Patient toPatient(PatientRequestDTO patientDto) {
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setAddress(patientDto.getAddress());
        patient.setEmail(patientDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientDto.getDateOfBirth()));
        patient.setRegisteredAt(LocalDate.parse(patientDto.getRegisteredDate()));
        return patient;
    }
}
