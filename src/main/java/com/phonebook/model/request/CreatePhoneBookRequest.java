package com.phonebook.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatePhoneBookRequest implements Serializable {

    private static final long serialVersionUID = 2524500677718037698L;

    @JsonProperty("firstName")
    @NotBlank(message = "Cannot be null or empty")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    @Email(message = "Invalid format")
    private String email;

    @JsonProperty("phoneNumber")
    @NotBlank(message = "Cannot be null or empty")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

}
