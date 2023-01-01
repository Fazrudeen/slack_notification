package com.samplejava.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest implements Serializable {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

}
