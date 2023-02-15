package com.phonebook.service;

import com.phonebook.model.request.CreatePhoneBookRequest;
import com.phonebook.model.request.UpdatePhoneBookRequest;
import com.phonebook.model.response.GetAllPhoneBookResponse;
import com.phonebook.model.response.GetPhoneBookByIdResponse;

import java.util.List;
import java.util.UUID;

public interface PhoneBookService {

    void create(CreatePhoneBookRequest request);

    List<GetAllPhoneBookResponse> getAll();

    GetPhoneBookByIdResponse getById(UUID id);

    void update(UUID id, UpdatePhoneBookRequest request);

    void deleteAll();

    void deleteById(UUID id);

}
