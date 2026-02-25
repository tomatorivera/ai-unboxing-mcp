package com.tomasrivera.fridge_manager.app.mcp.tools;

import com.tomasrivera.fridge_manager.app.models.FridgeItem;
import com.tomasrivera.fridge_manager.app.repositories.IFridgeItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FridgeItemToolsProvider {

    private final IFridgeItemRepository itemRepository;
    private final Logger log = LoggerFactory.getLogger(FridgeItemToolsProvider.class);

    public FridgeItemToolsProvider(IFridgeItemRepository fridgeItemRepository) {
        this.itemRepository = fridgeItemRepository;
    }

    @McpTool(name = "get_fridge_items", description = "Provides the full list of items in the fridge")
    public List<FridgeItem> getItems() {
        return itemRepository.findAll();
    }

    @McpTool(name = "save_frigde_item", description = "Allows to save the fridge item in a CSV file")
    public void saveItem(
            @McpToolParam(required = true, description = "Full data of the fridge item") FridgeItem item
    ) {
        log.info("### NEW ITEM -> " + item.toString() +" ###");
        itemRepository.save(item);
    }

    @McpTool(name = "get_items_by_category", description = "Provides the full list of items in a provided category")
    public List<FridgeItem> getItemsByCategory(
        @McpToolParam(required = true, description = "The category to match in the items") String category
    ) {
        return itemRepository.findByCategory(category);
    }

}
