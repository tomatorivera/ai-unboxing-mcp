package com.tomasrivera.fridge_manager.app.repositories;

import com.tomasrivera.fridge_manager.app.models.FridgeItem;

import java.util.List;

// To-Do: implement more utilities based on the fridge utils from the original exercise.
public interface IFridgeItemRepository {
    List<FridgeItem> findAll();
}
