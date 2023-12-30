package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.exception.ExceptionMessages;
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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Get list of attendance for one course by ID, requires a valid JWT with role ADMIN or TEACHER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of user's attendance for one course",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "Course Not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @GetMapping("{idCourse}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ResponseEntity<List<UserAttendanceListDto>> getUserAttendanceListByIdCourse (@PathVariable Integer idCourse){
        return new ResponseEntity<>(attendanceService.getUserAttendanceListByIdCourse(idCourse), HttpStatus.OK);
    }

    @Operation(summary = "Get list of attendance for one user to one course by IDs, requires a valid JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's attendance list for one course",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "Inscription not found by given IDs",
                    content ={ @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @GetMapping ("{idCourse}/{idUser}")
    public ResponseEntity<List<AttendanceDto>> getListAttendanceUserByIds(@PathVariable Integer idCourse, @PathVariable Integer idUser){
        return new ResponseEntity<>(attendanceService.getListAttendanceUserByIds(idCourse , idUser), HttpStatus.OK);
    }
    @Operation(summary = "Set attendance, if its not exist, it will be true by default, but if it already exist, will set the opossite status, requires a valid JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "406", description = "Not acceptable, Error as result of sending invalid data, for example, invalid date ",
                    content ={ @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "404", description = "Attendance not found by given ID",
                    content ={ @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict, Error as result of already reported attendance. If you wish to update attendance, please send Id_att.",
                    content ={ @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @PostMapping("setAttendance")
    public ResponseEntity<AttendanceDto> setAttendance (@RequestBody AttendanceDto attendanceDto){
        return new ResponseEntity<>(attendanceService.setAttendance(attendanceDto), HttpStatus.CREATED);
    }
}
