package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.entities.AuthResponse;
import com.example.JAM23.demo.auth.entities.LoginRequest;
import com.example.JAM23.demo.auth.entities.LoguedUserDetails;
import com.example.JAM23.demo.auth.entities.RegisterRequest;
import com.example.JAM23.demo.exception.ExceptionMessages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {
    private final AuthService authService;

// TODO => AGREGAR ENDPOINT Y LOGICA PARA BLOQUEAR USUARIOS... BUSCAR METODO isAccountNonLocked(), AGREGAR ENDPOINT Y LOGICA PARA
//  MANEJAR USUARIOS BLOQUEADOS, Deberiamos ver que es mejor, tal vez, la forma mas eficiente,
//  es por roles? tipo rol BLOQUED_USER?


    @Operation(summary = "This endpoint gets username and password and returns a JWT with credentials of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns JWT",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad credentials",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @PostMapping ("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return new ResponseEntity(authService.login(request) , HttpStatus.OK);
    }

    @Operation(summary = "This endpoint gets user's data and returns a JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns JWT",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)) }),
            @ApiResponse(responseCode = "406", description = "Error as result of sending invalid data, Ex: 'Password debe tener al menos 8 caracteres!' ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "409", description = "Error as result of sending data already reported, Ex: 'Datos ya existentes, revisa los campos!' ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @PostMapping ("register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return new ResponseEntity(authService.register(request) , HttpStatus.CREATED);
    }

    @Operation(summary = "This endpoint gets a JWT, and returns an Object with User details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns an Object with User details",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoguedUserDetails.class)) }),
            @ApiResponse(responseCode = "403", description = "JWT not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "500", description = "ESTE ERROR ESTA PENDIENTE DE DARLE OTRO MANEJO!!! NO ESTA BIEN EL CODIGO 500 ",
                    content = @Content) })
    @GetMapping ("user")
    public ResponseEntity<LoguedUserDetails> getLoguedUserDetails(@RequestHeader HttpHeaders headers){
        return new ResponseEntity(authService.getLoguedUserDetails(headers) , HttpStatus.OK);
    }
}
