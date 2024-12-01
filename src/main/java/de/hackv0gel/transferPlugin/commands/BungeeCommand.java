package de.hackv0gel.transferPlugin.commands;

import de.hackv0gel.transferPlugin.TransferPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BungeeCommand implements CommandExecutor {

    private final TransferPlugin plugin;

    public BungeeCommand(TransferPlugin plugin) {
        this.plugin = plugin;
    }

    private final String message = "§9This server is using V0gelCord {version}!\n" +
            "§9Download: https://github.com/PublicCode1337/V0gelCord\n" +
            "§9Support: https://dsc.gg/bka\n" +
            "§9YouTube: https://youtube.com/@HackV0geI";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String versionMessage = message.replace("{version}", plugin.getDescription().getVersion());
        sender.sendMessage(plugin.Prefix + versionMessage);
        return true;
    }
}
