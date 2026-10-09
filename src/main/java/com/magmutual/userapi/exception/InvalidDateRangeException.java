package com.magmutual.userapi.exception;

public class InvalidDateRangeException extends RuntimeException {

    public InvalidDateRangeException() {
        super("Start date cannot be after end date");
    }
}