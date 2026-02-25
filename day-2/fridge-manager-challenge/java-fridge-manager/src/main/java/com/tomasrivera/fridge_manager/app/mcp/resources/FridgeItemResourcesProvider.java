package com.tomasrivera.fridge_manager.app.mcp.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springaicommunity.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

import com.tomasrivera.fridge_manager.app.repositories.IFridgeItemRepository;

@Component
public class FridgeItemResourcesProvider {
  
  private final IFridgeItemRepository itemRepository;

  public FridgeItemResourcesProvider(IFridgeItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @McpResource(
    uri = "data://fridge/items/categories",
    name = "get_fridge_items_categories",
    description = "Provides a full list of fridge items categories"
  )
  public List<String> getCategories() {
    return itemRepository.findAll()
                        .stream()
                        .map(item -> item.category())
                        .filter(category -> category != null && !category.isEmpty())
                        .map(category -> category.trim())
                        .distinct()
                        .collect(Collectors.toList());
  }

}
