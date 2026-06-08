package blog.devrandom.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public record CoachData(CoachClass coachClass,
                        ToiletAvailabilityType toilet, boolean loadingSpecified,
                        String number) {
    public record ToiletAvailabilityType(ToiletStatus status, Value value) {
    }

    @JsonAdapter(CoachClass.Adapter.class)
    public enum CoachClass {
        FIRST("First"),
        MIXED("Mixed"),
        STANDARD("Standard");

        private final String value;

        CoachClass(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static CoachClass fromValue(String text) {
            for (CoachClass c : CoachClass.values()) {
                if (String.valueOf(c.value).equals(text)) {
                    return c;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<CoachClass> {
            @Override
            public void write(JsonWriter jsonWriter, CoachClass coachClass) throws IOException {
                jsonWriter.value(coachClass.getValue());
            }

            @Override
            public CoachClass read(JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return CoachClass.fromValue(String.valueOf(value));
            }
        }
    }


    @JsonAdapter(ToiletStatus.Adapter.class)
    public enum ToiletStatus {
        IN_SERVICE("InService"),
        NOT_IN_SERVICE("NotInService"),
        UNKNOWN("Unknown");
        private final String value;

        ToiletStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static ToiletStatus fromValue(String text) {
            for (ToiletStatus s : ToiletStatus.values()) {
                if (String.valueOf(s.value).equals(text)) {
                    return s;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<ToiletStatus> {
            @Override
            public void write(JsonWriter jsonWriter, ToiletStatus status) throws IOException {
                jsonWriter.value(status.getValue());
            }

            @Override
            public ToiletStatus read(JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return ToiletStatus.fromValue(String.valueOf(value));
            }
        }
    }

    @JsonAdapter(Value.Adapter.class)
    public enum Value {
        ACCESSIBLE("Accessible"),
        STANDARD("Standard"),
        UNKNOWN("Unknown"),
        NONE("None");

        private final String value;

        Value(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static Value fromValue(String text) {
            for (Value v : Value.values()) {
                if (String.valueOf(v.value).equals(text)) {
                    return v;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<Value> {

            @Override
            public void write(JsonWriter jsonWriter, Value value) throws IOException {
                jsonWriter.value(value.getValue());
            }

            @Override
            public Value read(JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return Value.fromValue(String.valueOf(value));
            }
        }

    }
}
