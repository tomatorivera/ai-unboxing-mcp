from mcp.server.fastmcp import FastMCP

# Create an MCP server
mcp = FastMCP("Simple MCP Server")

# Add an addition tool
@mcp.tool()
def add(a: int, b: int) -> int:
    """Add two numbers"""
    return a + b


# Add a dynamic greeting resource
@mcp.resource("greeting://{name}")
def get_greeting(name: str) -> str:
    """Get a personalized greeting"""
    return f"Hello, {name}!"


# Add a prompt
@mcp.prompt()
def greet_user(name: str, style: str = "friendly") -> str:
    """Generate a greeting prompt"""
    styles = {
        "friendly": "Please write a warm, friendly greeting",
        "formal": "Please write a formal, professional ghttp://localhost:6274/?MCP_PROXY_AUTH_TOKEN=bee8fbbe1b734f902d8aff3d8e1b54300fb26bb8f2cb09abb81c1acbc3691a99#resourcesreeting",
        "casual": "Please write a casual, relaxed greeting",
    }

    return f"{styles.get(style, styles['friendly'])} for someone named {name}."


def main():
    mcp.run()


if __name__ == "__main__":
    main()
