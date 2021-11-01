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
        discordWebhook.setAvatarUrl("https://static-cdn.jtvnw.net/jtv_user_pictures/fb81cae2-1aa2-44ba-a818-ecaa535ba9bd-profile_image-150x150.png");
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
        discordWebhook.setAvatarUrl("https://pic.onlinewebfonts.com/svg/img_569193.png");
        discordWebhook.setContent("Server " + this.server + " stopped :red_circle:");
        discordWebhook.execute();
    }
}
