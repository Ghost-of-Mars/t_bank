package clients;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//Решение проблемы с Cannot access io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory
//import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

public class ApiClientAccount {

    private String accessToken;

    private ConfigReader configReader;

    public ApiClientAccount () {
        this.configReader = new ConfigReader();
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.socket.timeout", 10000)
                .setParam("http.connection.timeout", 10000));

        RestAssured.config = RestAssured.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (aClass, s) -> {
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            return objectMapper;
                        }
                )
        );

        RestAssured.baseURI = this.configReader.getConfig().getUrl();
        accessToken = this.configReader.getConfig().getBearerToken();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Response get(String path) {
        return given()
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer" + accessToken)
                .and()
                .when()
                .get(path);
    }

// Метод для получения accessToken
//    public void authenticate() {
//        Response response = given()
//                .baseUri(configReader.getConfig().getUrlSso())
//                .relaxedHTTPSValidation()
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .formParam("first param", configReader.getConfig().getFirstParam)
//                .formParam("Last param", configReader.getConfig().getLastParam)
//                .when()
//                .post("path");
//    }

//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
}
