package com.example.JAM23.demo.auth.User;

import lombok.*;


@RequiredArgsConstructor
public enum Permission {
    READ_COURSES("Read all courses"),
    EDIT_COURSES("Edit any courses"),
    DELETE_COURSES("Delete any course");
    /*
    READ_COURSES(""),
    EDIT_COURSES(""),
    DELETE_COURSES("");
     */

    @Getter
    private final String permission;

}
