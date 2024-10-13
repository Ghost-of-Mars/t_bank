package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Перечесление регулярных выражений
 */
@Getter
@AllArgsConstructor
public enum RegularExpressions {

    REGEX_DATE_TIME(),
    REGEX_UU(),
    ANY_NUMBER(),
    ANY_VALUE_EXCEPT_AN_EMPTY_STRING(),
    ANY_VALUE(),
    NY_VALUE_EXCEPT_AN_NULL_STRING();

    private final String nameRegEx;
    private final String regEx;

    /**
     * Получает регулярное выражение
     *
     * @param name наименование регулярного выражения
     * @return regEx, если нет, то name
     */
    public static String getRegExByName(String name) {
        return Arrays.stream(RegularExpressions.values())
                .filter(regEx ->regEx.getNameRegEx().equals(name))
                .findFirst()
                .map(RegularExpressions::getRegEx)
                .orElse(name);
    }
}

