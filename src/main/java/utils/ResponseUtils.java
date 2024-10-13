package utils;

import io.restassured.response.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseUtils {

    public static void validateNonEmptyField (Response response, String jsonpath) {
        try {
            List<Object> fields = response.getBody().jsonPath().getList(jsonpath);
            validateField(fields);
        } catch (Exception e) {
            throw new AssertionError("Ошибка при проверке JSONPath" + jsonpath, e);
        }
    }

    private  static void validateField(List<Object> fields) {
        assertThat("Список полей null или пуст", fields, allOf(notNullValue(), not(empty())));
        assertThat("Список  содержит null элементы", fields, everyItem(notNullValue()));
    }
}
