package utils;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.Scenario;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetErrorInScenario {

    private static final Field field = FieldUtils.getField(Scenario.class, "delegate", true);
    private static Method getError;

    public static String getErrorInScenario(Scenario scenario) {
        try {
            final TestCaseState testCase = (TestCaseState) field.get(scenario);
            if (getError == null) {
                getError = MethodUtils.getMatchingMethod(testCase.getClass(), "getError");
                getError.setAccessible(true);
            }
            var err = (Throwable) getError.invoke(testCase);
            return err.getMessage();
        } catch (Exception e) {
            System.out.println("error receiving exception");
        }
        return "Не удалось получить текст ошибки падения автотества";
    }
}
