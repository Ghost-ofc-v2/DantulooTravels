package org.softwaregr5.dantulootravel.dantulootravel.domain.mappers;

import lombok.Data;

@Data

public class LoginResponse {
    String token;

    public LoginResponse() {
    }
    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}