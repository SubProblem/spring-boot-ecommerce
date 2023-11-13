package com.subproblem.securityservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    USER_READ("read"),
    USER_CREATE("create"),
    USER_UPDATE("update"),
    USER_DELETE("delete");

    private final String permission;

}
