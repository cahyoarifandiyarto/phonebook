package com.phonebook.service.impl;

import com.phonebook.entity.PhoneBook;
import com.phonebook.exception.NotFoundException;
import com.phonebook.helper.ValidationHelper;
import com.phonebook.model.request.CreatePhoneBookRequest;
import com.phonebook.model.request.UpdatePhoneBookRequest;
import com.phonebook.model.response.GetAllPhoneBookResponse;
import com.phonebook.model.response.GetPhoneBookByIdResponse;
import com.phonebook.repository.PhoneBookRepository;
import com.phonebook.service.PhoneBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneBookServiceImpl implements PhoneBookService {

    private final ValidationHelper validationHelper;

    private final PhoneBookRepository phoneBookRepository;

    @Override
    @Transactional
    public void create(CreatePhoneBookRequest request) {
        validationHelper.validate(request);

        PhoneBook phoneBook = PhoneBook.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();

        phoneBookRepository.save(phoneBook);
    }

    @Override
    public List<GetAllPhoneBookResponse> getAll() {
        List<PhoneBook> phoneBooks = phoneBookRepository.findAll();

        if (phoneBooks.isEmpty()) {
            return new ArrayList<>();
        }

        return phoneBooks.stream()
                .map(phoneBook -> GetAllPhoneBookResponse.builder()
                        .id(phoneBook.getId())
                        .firstName(phoneBook.getFirstName())
                        .lastName(phoneBook.getLastName())
                        .email(phoneBook.getEmail())
                        .phoneNumber(phoneBook.getPhoneNumber())
                        .address(phoneBook.getAddress())
                        .createdAt(phoneBook.getCreatedAt())
                        .updatedAt(phoneBook.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public GetPhoneBookByIdResponse getById(UUID id) {
        PhoneBook phoneBook = phoneBookRepository.findById(id)
                .orElseThrow(() -> {
                    Map<String, List<String>> errors = new HashMap<>();
                    errors.put("message", List.of("PhoneBook data with id " + id + " not found"));

                    throw new NotFoundException(errors);
                });

        return GetPhoneBookByIdResponse.builder()
                .id(phoneBook.getId())
                .firstName(phoneBook.getFirstName())
                .lastName(phoneBook.getLastName())
                .email(phoneBook.getEmail())
                .phoneNumber(phoneBook.getPhoneNumber())
                .address(phoneBook.getAddress())
                .createdAt(phoneBook.getCreatedAt())
                .updatedAt(phoneBook.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public void update(UUID id, UpdatePhoneBookRequest request) {
        PhoneBook phoneBook = phoneBookRepository.findById(id)
                .orElseThrow(() -> {
                    Map<String, List<String>> errors = new HashMap<>();
                    errors.put("message", List.of("PhoneBook data with id " + id + " not found"));

                    throw new NotFoundException(errors);
                });

        phoneBook.setFirstName(request.getFirstName());
        phoneBook.setLastName(request.getLastName());
        phoneBook.setEmail(request.getEmail());
        phoneBook.setPhoneNumber(request.getPhoneNumber());
        phoneBook.setAddress(request.getAddress());

        phoneBookRepository.save(phoneBook);
    }

    @Override
    @Transactional
    public void deleteAll() {
        List<PhoneBook> phoneBooks = phoneBookRepository.findAll();

        if (!phoneBooks.isEmpty()) {
            for (PhoneBook phoneBook : phoneBooks) {
                phoneBook.setIsDeleted(true);

                phoneBookRepository.save(phoneBook);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        PhoneBook phoneBook = phoneBookRepository.findById(id)
                .orElseThrow(() -> {
                    Map<String, List<String>> errors = new HashMap<>();
                    errors.put("message", List.of("PhoneBook data with id " + id + " not found"));

                    throw new NotFoundException(errors);
                });

        phoneBook.setIsDeleted(true);

        phoneBookRepository.save(phoneBook);
    }

}
