package com.tomasrivera.java_demo_server.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTools {

    private static final Logger log = LoggerFactory.getLogger(CalculatorTools.class);

    /* Some tools */

    @McpTool(name = "ping", description = "Simple test for the connection")
    public String ping() {
        return "pong";
    }

}
