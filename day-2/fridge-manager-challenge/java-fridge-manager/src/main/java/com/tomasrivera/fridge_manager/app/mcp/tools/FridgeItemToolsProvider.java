package com.tomasrivera.fridge_manager.app.mcp.tools;

import com.tomasrivera.fridge_manager.app.models.FridgeItem;
import com.tomasrivera.fridge_manager.app.repositories.IFridgeItemRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FridgeItemToolsProvider {

    private final IFridgeItemRepository itemRepository;

    public FridgeItemToolsProvider(IFridgeItemRepository fridgeItemRepository) {
        this.itemRepository = fridgeItemRepository;
    }

    @McpTool(name = "get_fridge_items", description = "Provides the full list of items in the fridge")
    public List<FridgeItem> getItems() {
        return itemRepository.findAll();
    }

}
