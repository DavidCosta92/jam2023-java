package com.example.JAM23.demo.auth.User;

import lombok.*;


@RequiredArgsConstructor
public enum Permission {
    READ_COURSES("READ_COURSES"),
    EDIT_COURSES("EDIT_COURSES"),
    DELETE_COURSES("DELETE_COURSES");
    @Getter
    private final String permission;

}
