package com.hexagonalarch.core.service.strategy;

import com.hexagonalarch.core.service.validations.ValidationResult;

public class NavigationResult<T> {
    private final ValidationResult validationResult;
    private final T result;

    private NavigationResult(ValidationResult validationResult, T result) {
        this.validationResult = validationResult;
        this.result = result;
    }

    public static <T> NavigationResult<T> success(T result, String message) {
        return new NavigationResult<>(new ValidationResult(true, message), result);
    }

    public static <T> NavigationResult<T> failure(String message) {
        return new NavigationResult<>(new ValidationResult(false, message), null);
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public T getResult() {
        return result;
    }
}