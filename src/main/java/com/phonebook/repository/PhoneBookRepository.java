package com.phonebook.repository;

import com.phonebook.entity.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, UUID> {
}
