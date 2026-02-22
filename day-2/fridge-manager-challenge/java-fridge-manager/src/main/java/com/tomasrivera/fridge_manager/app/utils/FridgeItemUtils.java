package com.tomasrivera.fridge_manager.app.utils;

import com.tomasrivera.fridge_manager.app.models.FridgeItem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FridgeItemUtils {
    public static FridgeItem fromCsvLine(String line) {
        String[] columns = line.split(",", -1);

        return new FridgeItem(
                columns[0].trim(),
                columns[1].trim(),
                parseDate(columns[2].trim()),
                parseDate(columns[3].trim()),
                columns[4].trim()
        );
    }

    private static LocalDate parseDate(String dateStr) {
        try {
            return (dateStr == null || dateStr.isEmpty()) ? null : LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String getItemInCsvFormat(FridgeItem data) {
        return data.itemName() + "," + data.category() + "," + data.purchaseDate() + "," + data.bestBeforeDate() + "," + data.quantity();
    }
}
