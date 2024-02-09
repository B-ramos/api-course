package com.br.course_api.domain.enums;

public enum ActiveEnum {
    ACTIVE,
    DISABLED;


    public ActiveEnum changeStatus() {
        return this == ACTIVE ? DISABLED : ACTIVE;
    }
}
