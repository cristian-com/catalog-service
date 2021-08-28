package com.cristian.buildingblocks.application.commands;

import java.util.Collections;
import java.util.List;

public record Error(String message, ErrorType errorType) {

    public List<Error> toList() {
        return Collections.singletonList(this);
    }

    public static Error application(String message) {
        return new Error(message, ErrorType.APPLICATION);
    }

    public static Error of(Exception exception) {
        return new Error(exception.getMessage(), ErrorType.APPLICATION);
    }

    enum ErrorType {
        APPLICATION, BUSINESS_RULE
    }

}
