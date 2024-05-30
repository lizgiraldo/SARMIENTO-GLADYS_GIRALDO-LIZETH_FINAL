package com.backend.clinicaDental.controller;

import com.backend.clinicaDental.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import com.backend.clinicaDental.service.IPacienteService;
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
    public  PacienteSalidaDto registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto){
        return pacienteService.registrarPaciente(pacienteEntradaDto);
    }

    @GetMapping("/listar")
    public List<PacienteSalidaDto> listarPaciente(){
        return pacienteService.listarPacientes();
    }


}
