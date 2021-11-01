package de.kyleonaut.serverstatus;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
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
        if (this.url == null){
            System.out.println("[ServerStatus] §cYou have to provide a webhook url!");
            return;
        }
        final DiscordWebhook discordWebhook = new DiscordWebhook(this.url);
        discordWebhook.setUsername("ServerStatus");
        discordWebhook.setAvatarUrl("https://www.radio.de/images/broadcasts/64/e5/122912/1/c300.png");
        discordWebhook.setContent("Server " + this.server + " started successfully :green_circle:");
        discordWebhook.execute();
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        if (this.url == null){
            return;
        }
        final DiscordWebhook discordWebhook = new DiscordWebhook(this.url);
        discordWebhook.setUsername("ServerStatus");
        discordWebhook.setAvatarUrl("https://www.radio.de/images/broadcasts/64/e5/122912/1/c300.png");
        discordWebhook.setContent("Server " + this.server + " stopped :red_circle:");
        discordWebhook.execute();
    }
}
