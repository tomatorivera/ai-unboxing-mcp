package com.tomasrivera.fridge_manager.app.repositories;

import com.tomasrivera.fridge_manager.app.models.FridgeItem;
import com.tomasrivera.fridge_manager.app.utils.FridgeItemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FridgeItemCsvRepository implements IFridgeItemRepository {

    private final Logger log = LoggerFactory.getLogger(FridgeItem.class);

    private static final String CSV_FILE_NAME = "fridge_contents.csv";
    private final ResourceLoader resourceLoader;
    private final Resource csvFile;

    public FridgeItemCsvRepository(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        csvFile = resourceLoader.getResource("classpath:static/".concat(CSV_FILE_NAME));
    }

    @Override
    public List<FridgeItem> findAll() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines()
                    .skip(1)
                    .filter(line -> !line.isBlank())
                    .map(FridgeItemUtils::fromCsvLine)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("CSV cannot be read: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
