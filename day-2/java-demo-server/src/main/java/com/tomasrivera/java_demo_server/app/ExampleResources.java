package com.tomasrivera.java_demo_server.app;

import org.springaicommunity.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExampleResources {

    @McpResource(uri = "tasks://todo", name = "my-tasks", description = "Provides information about my todo tasks")
    public List<String> getTodoTasks() {
        return List.of(
                "Complete this project testing all mcp capabilities in java",
                "Java > python, #javafan",
                "Complete the day-2 exercises (in java btw)",
                "ILY n8n"
        );
    }

}
