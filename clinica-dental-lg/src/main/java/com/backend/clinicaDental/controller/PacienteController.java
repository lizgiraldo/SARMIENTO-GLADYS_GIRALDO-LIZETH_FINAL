package com.backend.clinicaDental.controller;

import com.backend.clinicaDental.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import com.backend.clinicaDental.service.IPacienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes")

public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto){
        return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteEntradaDto), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteSalidaDto>> listarPaciente(){
        return new ResponseEntity<>(pacienteService.listarPacientes(),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PacienteSalidaDto> buscarPaciente(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    //put
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto, @PathVariable Long id){
       return new ResponseEntity<>(pacienteService.actulizarPaciente(pacienteEntradaDto, id), HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarPaciente(@RequestParam Long id){
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}
