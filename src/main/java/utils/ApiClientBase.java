package utils;

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
import lombok.Getter;

//Решение проблемы с Cannot access io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory
//import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

public class ApiClientBase {
    public static final ThreadLocal<Response> responseThreadLocal = new ThreadLocal<>();

    protected ConfigReader configReader = new ConfigReader();

    @Getter
    protected String accessToken;

    public ApiClientBase() {
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", 10000)
                        .setParam("http.connection.timeout", 10000))
                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (aClass, s) -> {
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            return objectMapper;
                        }
                )
        );
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public static  Response getResponse() {
        return responseThreadLocal.get();
    }
    public static void setResponse(Response response) {
        responseThreadLocal.set(response);
    }
    public static void clearThreadLocal(Response response) {
        responseThreadLocal.remove();
    }

//    //Метод для получения accessToken
//    protected void responseAuthenticate(String username, String password, String grantType, String urlKeycloak) {
//        setResponse(given()
//                .baseUri(urlKeycloak)
//                .relaxedHTTPSValidation()
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .formParam("first param", username)
//                .formParam("Last param", password)
//                .formParam("Last param", grantType)
//                .when()
//                .post("path"));
//
//    }
}
