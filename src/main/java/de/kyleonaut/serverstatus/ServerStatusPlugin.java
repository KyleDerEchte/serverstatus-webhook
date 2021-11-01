package de.kyleonaut.serverstatus;

import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 01.11.2021
 */
public class ServerStatusPlugin extends JavaPlugin {
    private String url;
    private String server;

    @SneakyThrows
    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.url = getConfig().getString("url");
        this.server = getConfig().getString("server");
        final DiscordWebhook discordWebhook = new DiscordWebhook(this.url);
        discordWebhook.setUsername("ServerStatus");
        discordWebhook.setAvatarUrl("https://pic.onlinewebfonts.com/svg/img_569193.png");
        discordWebhook.setContent("Server " + this.server + "started :green_circle:");
        discordWebhook.execute();
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        final DiscordWebhook discordWebhook = new DiscordWebhook(this.url);
        discordWebhook.setUsername("ServerStatus");
        discordWebhook.setAvatarUrl("https://pic.onlinewebfonts.com/svg/img_569193.png");
        discordWebhook.setContent("Server " + this.server + "stopped :red_circle:");
        discordWebhook.execute();
    }
}
