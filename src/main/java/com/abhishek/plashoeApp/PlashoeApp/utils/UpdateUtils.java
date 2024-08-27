package com.abhishek.plashoeApp.PlashoeApp.utils;

import java.lang.reflect.Field;
import java.util.Map;

public class UpdateUtils {
    public static <T> void applyUpdates(T entity, Map<String, Object> updates) {
        updates.forEach((key, value) -> {
            try {
                Field field = entity.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(entity, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}