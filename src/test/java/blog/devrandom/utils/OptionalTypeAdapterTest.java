package blog.devrandom.utils;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OptionalTypeAdapterTest {
    @Test
    @DisplayName("Serialize Optional with value present")
    public void optionalStringWithValue() {
        Gson gson = Utils.getGson();
        WithOptionalString withOptionalString = new WithOptionalString("one", Optional.of("hello"));
        assertEquals("{\"alwaysPresent\":\"one\",\"optionallyPresent\":\"hello\"}", gson.toJson(withOptionalString, WithOptionalString.class));
    }

    @Test
    @DisplayName("Serialize Optional without value")
    public void optionalStringWithoutValue() {
        Gson gson = Utils.getGson();
        WithOptionalString withOptionalString = new WithOptionalString("one", Optional.empty());
        assertEquals("{\"alwaysPresent\":\"one\"}", gson.toJson(withOptionalString, WithOptionalString.class));
    }

    @Test
    @DisplayName("Deserialize Optional without value")
    public void deserializeOptionalEmpty() {
        Gson gson = Utils.getGson();
        WithOptionalString withOptionalString = new WithOptionalString("yes", Optional.empty());
        assertEquals(withOptionalString, gson.fromJson("{'alwaysPresent':'yes'}", WithOptionalString.class));
    }

    @Test
    @DisplayName("Deserialize Optional with value")
    public void deserializeOptionalValue() {
        Gson gson = Utils.getGson();
        WithOptionalString withOptionalString = new WithOptionalString("yes", Optional.of("one"));
        assertEquals(withOptionalString, gson.fromJson("{'alwaysPresent':'yes', 'optionallyPresent': 'one'}", WithOptionalString.class));
    }

    record WithOptionalString(String alwaysPresent, Optional<String> optionallyPresent) {
        public WithOptionalString {
            //noinspection OptionalAssignedToNull
            if (optionallyPresent == null) {
                optionallyPresent = Optional.empty();
            }
        }
    }
}