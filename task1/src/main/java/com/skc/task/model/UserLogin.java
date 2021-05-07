package com.skc.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    //@NotNull(message = "Email account is required.")
    private String emailAccount;
    //@NotNull(message = "Password is required.")
    private String password;
}
