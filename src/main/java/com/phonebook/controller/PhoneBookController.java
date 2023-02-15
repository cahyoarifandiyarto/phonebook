package com.phonebook.controller;

import com.phonebook.helper.ResponseHelper;
import com.phonebook.model.request.CreatePhoneBookRequest;
import com.phonebook.model.request.UpdatePhoneBookRequest;
import com.phonebook.model.response.APIResponse;
import com.phonebook.model.response.GetAllPhoneBookResponse;
import com.phonebook.model.response.GetPhoneBookByIdResponse;
import com.phonebook.service.PhoneBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/phoneBooks")
@RequiredArgsConstructor
public class PhoneBookController {

    private final PhoneBookService phoneBookService;

    @PostMapping
    public ResponseEntity<APIResponse<Object>> create(@RequestBody CreatePhoneBookRequest request) {
        phoneBookService.create(request);

        return ResponseHelper.buildResponseEntity(HttpStatus.CREATED, null, null);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<GetAllPhoneBookResponse>>> getAll() {
        List<GetAllPhoneBookResponse> getAllPhoneBookResponses = phoneBookService.getAll();

        return ResponseHelper.buildResponseEntity(HttpStatus.OK, getAllPhoneBookResponses, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<GetPhoneBookByIdResponse>> getById(@PathVariable UUID id) {
        GetPhoneBookByIdResponse getPhoneBookByIdResponse = phoneBookService.getById(id);

        return ResponseHelper.buildResponseEntity(HttpStatus.OK, getPhoneBookByIdResponse, null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Object>> update(@PathVariable UUID id, @RequestBody UpdatePhoneBookRequest request) {
        phoneBookService.update(id, request);


        return ResponseHelper.buildResponseEntity(HttpStatus.NO_CONTENT, null, null);
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Object>> deleteAll() {
        phoneBookService.deleteAll();

        return ResponseHelper.buildResponseEntity(HttpStatus.NO_CONTENT, null, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Object>> deleteById(@PathVariable UUID id) {
        phoneBookService.deleteById(id);

        return ResponseHelper.buildResponseEntity(HttpStatus.NO_CONTENT, null, null);
    }

}
