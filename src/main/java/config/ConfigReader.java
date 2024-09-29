package config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ConfigReader {

    private Config config;

    public Config getConfig() {
        String TEST_ENV = System.getProperty("TEST_ENV", "default");
        String configFilePath = switch (TEST_ENV) {
            case "pre" -> "conf/config.yml";
            case "tst" -> "conf/config.yml";
            default -> "conf/config.yml";
        };
        System.out.println("Применена конфигурация = " + configFilePath);
        config = loadConfig(configFilePath);
        return config;
    }

    private Config loadConfig(String configFilePath) {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            Yaml yaml = new Yaml(new Constructor(Config.class));
            return (Config) yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Не найден файл конфигурации: " + configFilePath + e.getMessage());
        } catch (YAMLException e) {
            throw new RuntimeException("Некорректный синтаксис YAML формата файла конфигурации: " + configFilePath + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Не определённая ошибка при чтении файла конфигурации" + configFilePath + e.getMessage());
        }
    }
}
