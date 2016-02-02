package com.example.user.weather;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GeoAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> adapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement element = getTargetElement(adapter.read(in));
                return delegate.fromJsonTree(element);
            }
        };
    }

    private JsonElement getTargetElement(JsonElement element) {
        if (!element.isJsonObject()) {
            return element;
        }

        JsonObject obj = element.getAsJsonObject();

        if (obj.has("response")) {
            return obj.get("response");
        } else if (obj.has("location")) {
            return obj.get("location");
        } else {
            return element;
        }
    }

}
