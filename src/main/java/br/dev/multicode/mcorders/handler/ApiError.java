package br.dev.multicode.mcorders.handler;

import org.springframework.http.HttpStatus;

public record ApiError(
    int status,
    String message
) {}
