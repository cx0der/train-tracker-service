package blog.devrandom.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

@JsonAdapter(UncertaintyType.Status.Adapter.class)
public record UncertaintyType(Status status, String reason) {

    public enum Status {
        DELAY("Delay"),
        CANCELLATION("Cancellation"),
        OTHER("Other");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static Status fromValue(String text) {
            for (Status s : Status.values()) {
                if (String.valueOf(s.value).equals(text)) {
                    return s;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<Status> {

            @Override
            public void write(JsonWriter jsonWriter, Status status) throws IOException {
                jsonWriter.value(status.getValue());
            }

            @Override
            public Status read(JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return Status.fromValue(String.valueOf(value));
            }
        }
    }
}
