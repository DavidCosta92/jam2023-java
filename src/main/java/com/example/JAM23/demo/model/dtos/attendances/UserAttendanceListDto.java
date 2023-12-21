package com.example.JAM23.demo.model.dtos.attendances;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAttendanceListDto {
    private UserReadDto user;
    private List<AttendanceDto> attendanceList;
}
