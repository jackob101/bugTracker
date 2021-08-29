package com.trix.bugtracker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auth0Pojo {

    private String sub;
    private String email;
    private boolean emailVerified;
    private String nickname;
    private String name;

}
