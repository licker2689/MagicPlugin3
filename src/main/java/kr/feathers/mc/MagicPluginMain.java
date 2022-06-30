package kr.feathers.mc;

import kr.feathers.bot.MagicPluginBot;
import kr.feathers.mc.Listener.JoinQuit;
import kr.feathers.mc.commands.MPCommand;
import kr.feathers.mc.utils.ConfigUtils;
import kr.feathers.mc.utils.DataContainor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.logging.Logger;

import static kr.feathers.bot.MagicPluginBot.initJDA;
import static org.bukkit.Bukkit.getPluginCommand;
import static org.bukkit.Bukkit.getPluginManager;

@SuppressWarnings("all")
public class MagicPluginMain extends JavaPlugin implements CommandExecutor {
    private static MagicPluginMain plugin;
    public static YamlConfiguration config;
    private Logger log;
    public static String prefix;

    public static MagicPluginMain getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        log = getLogger();
        prefix = DataContainor.getPrefix();

        /* ## <- Setting Executor -> ## */
        getPluginCommand("mp").setExecutor(new MPCommand());

        /* ## <- Setting Event Listener -> ## */
        getPluginManager().registerEvents(new JoinQuit(), this);

        try {
            initJDA();
        }
        catch (LoginException e) {
            e.printStackTrace();
        }

        log.info("[ <- MagicPlugin Enabled -> ]");
    }

    @Override
    public void onDisable() {
        log.info("[ <- MagicPlugin Disabled -> ]");
    }
}

