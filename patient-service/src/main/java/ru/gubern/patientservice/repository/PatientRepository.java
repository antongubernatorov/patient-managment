package ru.gubern.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gubern.patientservice.model.Patient;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, UUID id);
}
