package com.skc.task.domain;

import com.skc.task.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private String token;
}
