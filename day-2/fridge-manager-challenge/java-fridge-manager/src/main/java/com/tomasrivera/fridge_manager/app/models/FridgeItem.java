package com.tomasrivera.fridge_manager.app.models;

import java.time.LocalDate;

public record FridgeItem(
        String itemName,
        String category,
        LocalDate purchaseDate,
        LocalDate bestBeforeDate,
        String quantity
) { }
