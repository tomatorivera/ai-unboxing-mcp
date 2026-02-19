package com.tomasrivera.java_demo_server.app;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpComplete;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamplePrompts {

    private List<String> names = List.of(
            "Tomas",
            "Ramiro",
            "Another guy",
            "John"
    );

    @McpPrompt(name = "greeting", description = "Generate a simple greeting message")
    public McpSchema.GetPromptResult greeting(
            @McpArg(name = "name", description = "User's name", required = true) String name)
    {
        String message = (name != null && !name.trim().isEmpty())
            ? "Hello, " + name + "! How can I help you today?"
            : "Mmmmh, i dont know you, please identify!";

        return new McpSchema.GetPromptResult(
                "Greeting",
                List.of(new McpSchema.PromptMessage(McpSchema.Role.ASSISTANT, new McpSchema.TextContent(message)))
        );
    }

    @McpComplete(prompt = "greeting")
    public List<String> completeName(String prefix) {
        return names.stream()
                .filter(name -> name.toLowerCase().startsWith(prefix.toLowerCase()))
                .limit(10)
                .toList();
    }

}
