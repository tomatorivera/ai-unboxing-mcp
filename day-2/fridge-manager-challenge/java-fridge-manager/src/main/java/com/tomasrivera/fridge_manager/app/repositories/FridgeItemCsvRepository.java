package com.tomasrivera.fridge_manager.app.repositories;

import com.tomasrivera.fridge_manager.app.models.FridgeItem;
import com.tomasrivera.fridge_manager.app.utils.FridgeItemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FridgeItemCsvRepository implements IFridgeItemRepository {

    private final Logger log = LoggerFactory.getLogger(FridgeItem.class);

    @Value("${fridgemanager.csv.path}")
    private String CSV_FILE_PATH;

    @Override
    public List<FridgeItem> findAll() {
        Path path = Paths.get(CSV_FILE_PATH);

        if (Files.notExists(path)) {
            log.warn("CSV file does not exist yet: {}", path.toAbsolutePath());
            return List.of();
        }

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return reader.lines()
                    .skip(1) // header
                    .filter(line -> !line.isBlank())
                    .map(FridgeItemUtils::fromCsvLine)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("Error reading CSV file", e);
            return List.of();
        }
    }

    @Override
    public List<FridgeItem> findByCategory(String category) {
        Path path = Paths.get(CSV_FILE_PATH);
        if (Files.notExists(path)) {
            log.warn("CSV file does not exist yet: {}", path.toAbsolutePath());
            return List.of();
        }

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return reader.lines()
                    .skip(1) // header
                    .filter(line -> !line.isBlank())
                    .map(FridgeItemUtils::fromCsvLine)
                    .filter(f -> f.category().equalsIgnoreCase(category))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("Error reading CSV file", e);
            return List.of();
        }
    }

    @Override
    public void save(FridgeItem item) {
        try
        {
            log.info("### PATH -> " + CSV_FILE_PATH + " ###");
            Path path = Paths.get(CSV_FILE_PATH);
            log.info("### PATH RESOLVED -> " + path.toAbsolutePath() + " ###");

            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                log.info("Writing...");
                writer.write(FridgeItemUtils.getItemInCsvFormat(item)
                            .replaceAll("null", ""));
                writer.newLine();
            }
        }
        catch (Exception e)
        {
            log.error("CSV cannot be write: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
