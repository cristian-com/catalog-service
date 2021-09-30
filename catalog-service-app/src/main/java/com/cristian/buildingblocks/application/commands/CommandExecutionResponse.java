package com.cristian.buildingblocks.application.commands;

import com.cristian.buildingblocks.domain.Aggregate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNullElse;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class CommandExecutionResponse<T extends Aggregate> {

    private final T response;
    private final Status status;
    private final List<Error> errors;
    private final List<Log> log;

    public CommandExecutionResponse(T response, Status status, List<Error> errors, List<Log> log) {
        this.response = response;
        this.status = status;
        this.errors = requireNonNullElse(errors, Collections.emptyList());
        this.log = requireNonNullElse(log, Collections.emptyList());
    }

    public static <T extends Aggregate> CommandExecutionResponse<T> failed(List<Error> errors) {
        return response((T) null, Status.FAILED)
                .errors(errors)
                .build();
    }

    public static <T extends Aggregate> CommandExecutionResponse<T> failed(T response, List<Error> errors) {
        return response(response, Status.FAILED)
                .errors(errors)
                .build();
    }

    public static <T extends Aggregate> CommandExecutionResponse<T> success(T response, List<Log> log) {
        return response(response, Status.SUCCESS)
                .log(log)
                .build();
    }

    public static <T extends Aggregate> CommandExecutionResponse<T> success(T response) {
        return response(response, Status.SUCCESS)
                .build();
    }

    @SuppressWarnings("unchecked")
    private static <T extends Aggregate> CommandExecutionResponseBuilder<T> response(T response, Status status) {

        return (CommandExecutionResponseBuilder<T>) builder()
                .response(response)
                .status(status);
    }

    private enum Status {
        FAILED, SUCCESS
    }

}
