package blog.devrandom.utils;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDateTimeAdapterTest {

    @Test
    @DisplayName("Serialize LocalDateTime")
    public void serializeLocalDateTime() {
        Gson gson = Utils.getGson();
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 1, 10, 10, 10);
        assertEquals("{\"dateTime\":\"2025-01-01T10:10:10\"}", gson.toJson(new WithLocalDateTime(dateTime)));
    }

    @Test
    @DisplayName("Deserialize LocalDateTime")
    public void deserializeLocalDateTime() {
        Gson gson = Utils.getGson();
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 1, 10, 10, 10);
        WithLocalDateTime withLocalDateTime = new WithLocalDateTime(dateTime);
        assertEquals(withLocalDateTime, gson.fromJson("{\"dateTime\":\"2025-01-01T10:10:10\"}", WithLocalDateTime.class));
    }

    record WithLocalDateTime(LocalDateTime dateTime) {
    }
}
