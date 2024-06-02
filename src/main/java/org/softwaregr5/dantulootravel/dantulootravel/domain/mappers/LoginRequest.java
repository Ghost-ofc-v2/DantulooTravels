package org.softwaregr5.dantulootravel.dantulootravel.domain.mappers;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;



}