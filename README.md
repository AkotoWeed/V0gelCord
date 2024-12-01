# V0gelCord

**V0gelCord** is a Bukkit plugin for Minecraft that allows players to transfer between different servers using commands. It supports multiple languages and provides simple commands for server transfers and BungeeCord integration.

## Features

- **Server Transfer**: Allows players to switch between different servers.
- **BungeeCord Information**: Displays information about the BungeeCord version in use and useful links.
- **Multilingual Support**: Provides messages in multiple languages for a user-friendly experience.

## Installation

1. **Download the Plugin**: [V0gelCord-1.2.jar](https://example.com/download)
2. **Place the JAR file** in your server's `plugins` folder.
3. **Restart your server** or reload the plugin to activate it.

## Configuration

### `config.yml`

The default configuration file `config.yml` will be created on the first start of the plugin. Key options include:

- `language`: The default language to be used (e.g., `en` for English, `de` for German).

### Language Files

Language files are stored in the `langs` folder within the plugin directory. Available language files are:

- `messages_en.yml` – English
- `messages_de.yml` – German
- `messages_lu.yml` – Luxembourgish
- `messages_es.yml` – Spanish
- `messages_it.yml` – Italian
- `messages_fr.yml` – French
- `messages_tr.yml` – Turkish

### Server Configuration

Server configuration is done in `config.yml`. You can define server addresses and names for the server transfer commands:

```yaml
servers:
  example:
    address: "example.com:25565"
  another:
    address: "another.example.com:12345"
```

## Commands

### `/server <server_name>`

Transfers to the specified server. Only players can execute this command.

- **Example**: `/server example`

### `/bungee`

Displays information about the BungeeCord server in use.

- **Example**: `/bungee`

## Messages

The plugin uses a prefix for all messages. By default, the prefix is:

```
V0gelCord §7»
```

The exact messages for errors, successes, and other notifications can be customized in the language files.

## Troubleshooting

- **Commands not working**: Ensure that the plugin is correctly installed and enabled. Check the server console for errors during plugin startup.
- **Error messages with `/server` command**: Check `config.yml` to make sure server addresses are configured correctly.

## Support

For support and feedback:

- **GitHub Repository**: [PublicCode1337/V0gelCord](https://github.com/PublicCode1337/V0gelCord)
- **Discord Server**: [Support](https://dsc.gg/bka)
- **YouTube Channel**: [HackV0geI](https://youtube.com/@HackV0geI)