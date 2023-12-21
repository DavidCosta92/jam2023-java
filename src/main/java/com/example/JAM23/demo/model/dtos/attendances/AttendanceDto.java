package com.example.JAM23.demo.model.dtos.attendances;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {
    private Integer id_att;
    private Integer id_inscription;
    private LocalDate date;
    private boolean present;
}



