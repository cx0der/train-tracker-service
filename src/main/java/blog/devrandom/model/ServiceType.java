package blog.devrandom.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

@JsonAdapter(ServiceType.Adapter.class)
public enum ServiceType {
    TRAIN("train"),
    BUS("bus"),
    FERRY("ferry");

    private final String value;

    ServiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ServiceType fromValue(String text) {
        for (ServiceType t : ServiceType.values()) {
            if (String.valueOf(t.value).equals(text)) {
                return t;
            }
        }
        return null;
    }

    public static class Adapter extends TypeAdapter<ServiceType> {

        @Override
        public void write(JsonWriter jsonWriter, ServiceType serviceType) throws IOException {
            jsonWriter.value(serviceType.getValue());
        }

        @Override
        public ServiceType read(JsonReader jsonReader) throws IOException {
            String value = jsonReader.nextString();
            return ServiceType.fromValue(String.valueOf(value));
        }
    }
}