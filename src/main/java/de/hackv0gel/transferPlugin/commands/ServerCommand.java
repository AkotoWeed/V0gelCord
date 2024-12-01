package de.hackv0gel.transferPlugin.commands;

import de.hackv0gel.transferPlugin.TransferPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ServerCommand implements CommandExecutor, TabCompleter {
    private final TransferPlugin plugin;

    public ServerCommand(TransferPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(formatMessage("only_player"));
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(formatMessage("provide_server_name"));
            return false;
        }

        Player player = (Player) sender;
        String serverName = args[0];
        String path = "servers." + serverName + ".address";
        String address = plugin.getConfig().getString(path);

        if (address == null) {
            player.sendMessage(formatMessage("server_not_found").replace("{server}", serverName));
            return false;
        }

        String[] addressParts = address.split(":");
        if (addressParts.length != 2) {
            player.sendMessage(formatMessage("invalid_address").replace("{address}", address));
            return false;
        }

        String host = addressParts[0];
        int port;

        try {
            port = Integer.parseInt(addressParts[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(formatMessage("invalid_port").replace("{port}", addressParts[1]));
            return false;
        }

        player.transfer(host, port);
        player.sendMessage(formatMessage("transferring").replace("{server}", serverName));

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> serverNames = new ArrayList<>();
            if (plugin.getConfig().getConfigurationSection("servers") != null) {
                for (String key : plugin.getConfig().getConfigurationSection("servers").getKeys(false)) {
                    serverNames.add(key);
                }
            }
            return serverNames;
        }
        return null;
    }

    private String formatMessage(String key) {
        String message = plugin.getMessage(key);
        return plugin.Prefix + " " + message;
    }
}
