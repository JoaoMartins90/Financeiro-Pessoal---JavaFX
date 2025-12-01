package com.example.financeiro.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter writer, LocalDate date) throws IOException {
        writer.value(date.toString()); // "2025-11-30"
    }

    @Override
    public LocalDate read(JsonReader reader) throws IOException {
        return LocalDate.parse(reader.nextString());
    }
}
