package com.hexagonalarch.core.service.validations;

public interface Validator<T> {
    ValidationResult validate(T entity);
}
