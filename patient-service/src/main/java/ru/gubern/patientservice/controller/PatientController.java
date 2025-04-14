package ru.gubern.patientservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gubern.patientservice.dto.PatientRequestDTO;
import ru.gubern.patientservice.dto.PatientResponseDTO;
import ru.gubern.patientservice.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientDto) {
        return ResponseEntity.ok().body(patientService.createPatient(patientDto));
    }
}
