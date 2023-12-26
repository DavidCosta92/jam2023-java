package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.model.dtos.attendances.AttendanceDto;
import com.example.JAM23.demo.model.dtos.attendances.UserAttendanceListDto;
import com.example.JAM23.demo.model.entities.AttendanceEntity;
import com.example.JAM23.demo.services.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    // TODO VALIDACIONES, DE FECHAS, PODRIAMOS CALCULAR LA FECHA DE INGRESO POR SISTEMA, PARA QUE SEA CORRECTA,
    //  PERO DEBERIAMOS DE ALGUNA MANERA, HACER QUE EL ENDPOINT DE ASISTENCIA VALIDE SI SE ESTA REGISTRANDO
    //  LA ASIST EN DETERMINADA FRANJA HORARIA, SI NO QUE NO LO PERMITA?

    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO => Cual seria el caso practico? equivocarnos de user o de curso?



    // TODO VER TODAS LAS ASISTENCIAS DE UN CURSO (osea mostrar el listado de todos los usuarios y sus asistencias a un curso)


    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @GetMapping("{idCourse}")
    public ResponseEntity<List<UserAttendanceListDto>> getUserAttendanceListByIdCourse (@PathVariable Integer idCourse){
        return new ResponseEntity<>(attendanceService.getUserAttendanceListByIdCourse(idCourse), HttpStatus.OK);
    }

    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @GetMapping ("{idCourse}/{idUser}")
    public ResponseEntity<List<AttendanceDto>> getListAttendanceUserByIds(@PathVariable Integer idCourse, @PathVariable Integer idUser){
        return new ResponseEntity<>(attendanceService.getListAttendanceUserByIds(idCourse , idUser), HttpStatus.OK);
    }
    @Operation(summary = "Set attendance, if its not exist, it will be true by default, but if it already exist, will set the opossite status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @PostMapping("setAttendance")
    public ResponseEntity<AttendanceDto> setAttendance (@RequestBody AttendanceDto attendanceDto){
        return new ResponseEntity<>(attendanceService.setAttendance(attendanceDto), HttpStatus.OK);
    }
}
