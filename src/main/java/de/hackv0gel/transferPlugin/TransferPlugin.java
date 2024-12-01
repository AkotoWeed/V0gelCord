package de.hackv0gel.transferPlugin;

import de.hackv0gel.transferPlugin.commands.BungeeCommand;
import de.hackv0gel.transferPlugin.commands.ServerCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class TransferPlugin extends JavaPlugin {

    private FileConfiguration config;
    private Map<String, FileConfiguration> languageFiles = new HashMap<>();
    private String defaultLanguage = "en";

    public static TransferPlugin Instance;
    public String Prefix = "§x§0§1§b§0§d§6V§x§2§1§9§c§d§a0§x§4§0§8§9§d§eg§x§6§0§7§5§e§1e§x§7§f§6§2§e§5l§x§9§f§4§e§e§9C§x§b§e§3§a§e§do§x§d§e§2§7§f§0r§x§f§d§1§3§f§4d §7• ";
    public String Version = "1.0";

    public static TransferPlugin getInstance() {
        return Instance;
    }

    @Override
    public void onEnable() {
        Instance = this;
        saveDefaultConfig();
        this.config = getConfig();
        getLogger().info(Prefix + "§aLoaded Successfully!");

        loadLanguageFiles();

        ServerCommand commandExecutor = new ServerCommand(this);
        BungeeCommand bungeeCommand = new BungeeCommand(this);
        getCommand("server").setExecutor(commandExecutor);
        getCommand("server").setTabCompleter(commandExecutor);
        getCommand("bungee").setExecutor(bungeeCommand);
    }

    @Override
    public void onDisable() {
        getLogger().info(Prefix + "§cDisabled Successfully!");
    }

    public FileConfiguration getPluginConfig() {
        return config;
    }

    private void loadLanguageFiles() {
        File langFolder = new File(getDataFolder(), "langs");
        if (!langFolder.exists()) {
            langFolder.mkdir();
        }

        String[] languages = {"en", "de", "lu", "es", "it", "fr", "tr"};
        for (String lang : languages) {
            File langFile = new File(langFolder, "messages_" + lang + ".yml");
            if (!langFile.exists()) {
                saveResource("langs/messages_" + lang + ".yml", false);
            }
            FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);
            languageFiles.put(lang, langConfig);
        }
    }

    public FileConfiguration getLanguageFile(String lang) {
        return languageFiles.getOrDefault(lang, languageFiles.get(defaultLanguage));
    }

    public String getConfigLanguage() {
        return getConfig().getString("language", defaultLanguage); // Default-Wert ist "en"
    }

    public String getMessage(String key) {
        String lang = getConfigLanguage();
        FileConfiguration config = getLanguageFile(lang);
        return config.getString(key, "Message not found!");
    }
}
