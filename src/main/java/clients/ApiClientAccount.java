package clients;

import io.restassured.RestAssured;
import utils.ApiClientBase;

//import java.util.Objects;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.MatcherAssert.assertThat;


public class ApiClientAccount extends ApiClientBase {

    public ApiClientAccount() {
        RestAssured.baseURI = this.configReader.getConfig().getUrl();
        accessToken = this.configReader.getConfig().getBearerToken();
    }

    public void get(String path) {
        setResponse(given()
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer" + accessToken)
                .and()
                .when()
                .get(path));
    }

    /////////////////////////
//    public Response get(String path) {
//        return given()
//                .relaxedHTTPSValidation()
//                .header("Authorization", "Bearer" + accessToken)
//                .and()
//                .when()
//                .get(path);
//    }
    /////////////////////////

//    public void authenticate() {
//        responseAuthenticate(
//                configReader.getConfig().getUsername(),
//                configReader.getConfig().getPassword(),
//                configReader.getConfig().getGrantType(),
//                configReader.getConfig().getUrlKeycloak()
//        );
//        accessToken = getResponse().jsonPath().getString("access_token");
//        assertThat(String.format("Не удалось получить токен. Ответ - %s", getResponse().getBody().asString()), Objects.nonNull(accessToken));
//
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
}
