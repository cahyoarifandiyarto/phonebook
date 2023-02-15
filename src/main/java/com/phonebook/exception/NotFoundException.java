package com.phonebook.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {

    private final Map<String, List<String>> errors;

}
