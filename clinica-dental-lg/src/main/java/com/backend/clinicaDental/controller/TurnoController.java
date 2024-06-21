package com.backend.clinicaDental.controller;


import com.backend.clinicaDental.dto.entrada.TurnoEntradaDto;

import com.backend.clinicaDental.dto.salida.TurnoSalidaDto;
import com.backend.clinicaDental.exceptions.ResourceNotFoundException;
import com.backend.clinicaDental.service.ITurnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("turnos")
@CrossOrigin
public class TurnoController {

    private ITurnosService turnoService;

    @Autowired

    public TurnoController(ITurnosService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public TurnoSalidaDto guardarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto) {
        return turnoService.registrarTurno(turnoEntradaDto);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoSalidaDto> buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.actualizarTurno(id, turnoEntradaDto), HttpStatus.OK);
    }


    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarTurno(@RequestBody Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado", HttpStatus.NO_CONTENT);
    }


}
