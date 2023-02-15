package com.phonebook.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class APIResponse<T> implements Serializable {

    private static final long serialVersionUID = 4795145542814227999L;

    @JsonProperty("status")
    private String status;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    private T data;

    @JsonProperty("errors")
    private Map<String, List<String>> errors;

}
