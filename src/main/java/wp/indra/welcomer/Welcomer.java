package wp.indra.welcomer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import wp.indra.commands.main.MainCommand;
import wp.indra.utils.MessageUtils;

public final class Welcomer extends JavaPlugin {
    //Prefix del plugin
    public static String prefix =
            "&7[ &aKimetsu Werlcomer &7] ";

    private  String version =
            getDescription().getVersion();


    public void onEnable() {
        // Mensajes en consola cuando el plugin se inicia
        registerCommands();

        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage(
                prefix + "&7&lA iniciado Correctamente version: " + version
        ));

        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage(
                "Gracias por usar " + prefix + "Hasta pronto"
        ));

    }


    public void onDisable() {
        // mensajes en consola del plugin cunado finaliza
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage(
                prefix + "&7&lA Finalizado Correctamente version: " + version
                ));

        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage(
                "Gracias por usar " + prefix + "Hasta pronto"
                ));
    }

    public void registerCommands(){
        this.getCommand("welcomer").setExecutor(new MainCommand(this));
    }
}
