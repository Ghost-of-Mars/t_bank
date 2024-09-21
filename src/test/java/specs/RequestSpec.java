package specs;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpec {
    public static RequestSpecification reqSpec = with()
            .baseUri("https://business.tbank.ru/openapi/sandbox/api/")
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(JSON);
}