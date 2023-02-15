package com.phonebook.helper;

import com.phonebook.model.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ResponseHelper {

    public static <T> ResponseEntity<APIResponse<T>> buildResponseEntity(HttpStatus httpStatus, T data, Map<String, List<String>> errors) {
        APIResponse<T> apiResponse = APIResponse.<T>builder()
                .status(httpStatus.name())
                .code(httpStatus.value())
                .data(data)
                .errors(errors)
                .build();

        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

}
