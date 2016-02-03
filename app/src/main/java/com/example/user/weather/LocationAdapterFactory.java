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

public class LocationAdapterFactory implements TypeAdapterFactory {

    private static final String RESPONSE = "response";
    private static final String AREA = "area";
    private static final String PREFECTURE = "prefecture";
    private static final String LOCATION = "location";

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

        if (obj.has(RESPONSE)) {
            JsonObject response = obj.get(RESPONSE).getAsJsonObject();
            if (response.has(AREA)) {
                return response.get(AREA);
            } else if (response.has(PREFECTURE)) {
                return response.get(PREFECTURE);
            } else if (response.has(LOCATION)) {
                return response.get(LOCATION);
            }
        }

        return element;
    }

}