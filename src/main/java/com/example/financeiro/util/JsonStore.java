package com.example.financeiro.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonStore {

    private static final String FILE = "transactions.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static <T> List<T> loadList(Class<T> clazz) {
        try (FileReader reader = new FileReader(FILE)) {
            Type listType = TypeToken.getParameterized(List.class, clazz).getType();
            List<T> data = gson.fromJson(reader, listType);
            return data != null ? data : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static <T> void saveList(List<T> list) {
        try (FileWriter writer = new FileWriter(FILE)) {
            gson.toJson(list, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
