package blog.devrandom.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record StationBoard(
        LocalDateTime generatedAt,
        String locationName,
        String crs,
        String filterLocationName,
        @SerializedName("filtercrs")
        String filterCrs,
        FilterType filterType,
        Optional<List<NrccMessage>> nrccMessages,
        Optional<Boolean> platformAvailable,
        Optional<Boolean> areServicesAvailable,
        Optional<List<ServiceItemWithCallingPoints>> trainServices,
        Xmlns xmlns) {

    @SuppressWarnings("OptionalAssignedToNull")
    public StationBoard {
        // GSON sets the optional values as null
        if (platformAvailable == null) {
            platformAvailable = Optional.empty();
        }
        if (areServicesAvailable == null) {
            areServicesAvailable = Optional.empty();
        }
        if (nrccMessages == null) {
            nrccMessages = Optional.empty();
        }
        if (trainServices == null) {
            trainServices = Optional.empty();
        }
    }

    @JsonAdapter(FilterType.Adapter.class)
    public enum FilterType {
        TO("to"),
        FROM("from");

        private final String value;

        FilterType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static FilterType fromValue(String text) {
            for (FilterType t : FilterType.values()) {
                if (String.valueOf(t.value).equals(text)) {
                    return t;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<FilterType> {
            @Override
            public void write(JsonWriter jsonWriter, FilterType filterType) throws IOException {
                jsonWriter.value(filterType.getValue());
            }

            @Override
            public FilterType read(JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return FilterType.fromValue(String.valueOf(value));
            }
        }
    }
}
