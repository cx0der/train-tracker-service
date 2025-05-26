package blog.devrandom.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public class OptionalTypeAdapter<E> implements JsonSerializer<Optional<E>>, JsonDeserializer<Optional<E>> {

    @Override
    public Optional<E> deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        if (json.isJsonNull()) {
            return Optional.empty();
        }
        E value = context.deserialize(json, ((ParameterizedType) type).getActualTypeArguments()[0]);
        return Optional.ofNullable(value);
    }

    @Override
    public JsonElement serialize(Optional<E> src, Type type, JsonSerializationContext context) {
        if (src.isPresent()) {
            return context.serialize(src.get());
        }
        return JsonNull.INSTANCE;
    }
}
