package com.TgBotMOEVM.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    String access_token;
    String refresh_token;
    String expires_in;
}
