package utils;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public final class Storage {

    private static final ThreadLocal<HashMap<String, Object>> storage = ThreadLocal.withInitial(HashMap::new);

    private Storage () {

    }

    /**
     *
     * @param key
     * @param object
     * @param canBeNull
     * @throws IllegalStateException
     */
    public static void put(@NonNull String key, Object object, boolean canBeNull) {
        if (object == null && !canBeNull) {
            throw new IllegalStateException("Переданный объект равен null");
        }
        storage.get().put(key, object);
    }

    /**
     *
     * @param key
     * @param object
     * @throws IllegalStateException
     */
    public static void put(@NonNull String key, Object object) {
        put(key, object, false);
    }

    /**
     *
     * @param key
     * @return
     * @param <T>
     * @throws IllegalStateException
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(@NonNull String key) {
        Object o = storage.get().get(key.trim());
        if (o == null) {
            throw new IllegalStateException("\"" + key + "\" не существует в хранилище");
        }
        return (T) o;
    }

    public static void putAll(@NonNull Map<String, String> map) {
        storage.get().putAll(map);
    }
    public static void putAllObject(@NonNull Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            var value = entry.getValue();
            if (Objects.isNull(value)) {
                value = "null";
            }
            storage.get().put(entry.getKey(), value);
        }
    }

    /**
     *
     */
    public static void clear() {
        storage.get().clear();
    }
}
