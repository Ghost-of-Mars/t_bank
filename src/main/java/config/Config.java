package config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    private String url;
    private String bearerToken;

    // Данные для получения accessToken`a
    private String urlKeycloak;
    private String username;
    private String password;
    private String grantType;

}
