package com.phonebook.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "phone_books")
@DynamicInsert
@DynamicUpdate
@Where(clause = "is_deleted=false")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhoneBook implements Serializable {

    private static final long serialVersionUID = -3678703390165141272L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        createdAt = System.currentTimeMillis();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = System.currentTimeMillis();
    }

}
