package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public class ResponseSpec {
    public static ResponseSpecification respSpec200 = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

}
