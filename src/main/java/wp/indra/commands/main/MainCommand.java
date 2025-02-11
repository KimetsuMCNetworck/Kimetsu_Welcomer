package wp.indra.commands.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wp.indra.utils.MessageUtils;
import wp.indra.welcomer.Welcomer;

public class MainCommand implements CommandExecutor {

    private Welcomer plugin;
    public MainCommand(Welcomer plugin){
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        if(!(sender instanceof Player)){
            //consola
            sender.sendMessage(MessageUtils.getColoredMessage(
                    Welcomer.prefix +
                            "&fEl Comando solo se puede ejecutar por un &f&l[ &c&lAdministrador &f&l] &fdesde el juego"
            ));
            return true;
        }
        //aqui se obtienen los datos de un jugador
        Player player = (Player) sender;

        //argumentos de /welcomer (obciones que va a tener el plugin)
        if(args.length >= 1){
            if(args[0].equalsIgnoreCase("reload")
                    || args[0].equalsIgnoreCase("get")
                    || args[0].equalsIgnoreCase("help")
                    || args[0].equalsIgnoreCase("test")){
                switch (args[0]){
                    case "reload":
                        Welcomer.getPlugin(Welcomer.class).reloadConfig();
                        sender.sendMessage(MessageUtils.getColoredMessage(
                                Welcomer.prefix + "&aConfiguracion recargada correctamente"
                        ));
                        break;
                    case "get":
                        SubCommandGet(sender, args);
                        break;
                    case "help":
                        help(sender);
                        break;

                    case "test":
                        sender.sendMessage(MessageUtils.getColoredMessage(
                                Welcomer.prefix + "&aProximamente"
                        ));
                        break;
            }
            }
        }else{
            //Esto solo se ejecuta si el usuario pone /welcomer sin [Argumentos]
            help(sender);
        }

        return true ;
    }
    public void help(CommandSender sender) {
        sender.sendMessage(MessageUtils.getColoredMessage(
                Welcomer.prefix +
                        "&6-----&7&lComandos &fdel &8&lPlugin &6-----"
        ));
        sender.sendMessage(MessageUtils.getColoredMessage(
                Welcomer.prefix + "&6- /welcomer reload &7Con este comando recargas el &f&lPlugin"
        ));

        sender.sendMessage(MessageUtils.getColoredMessage(
                Welcomer.prefix + "&6- /welcomer get [autor / version] &7con esto ves la información de la &f&lvercion y del &f&lautor del plugin"
        ));

        sender.sendMessage(MessageUtils.getColoredMessage(
                Welcomer.prefix + "&6- /welcomer help  &7Con este comando puedes ver todos los &f&lcomandos del &f&lplugin "
        ));
        sender.sendMessage(MessageUtils.getColoredMessage(
                Welcomer.prefix + "&6- /welcomer test &7Haces un &f&lTest &fsin nesesidad de &f&lsalirte y &f&lvolver a entrar del &f&lServidor"
        ));

    }

    //Metodo de los segundos argumentos de welcomer [autor / version]
    public void SubCommandGet(CommandSender sender, String[] args){

        if(args.length == 1){
            sender.sendMessage(MessageUtils.getColoredMessage(
                    Welcomer.prefix + "&6 debes usar /welcomer get [autor / version]"
            ));
            return;
        }
        if(args[1].equalsIgnoreCase("autor")
                || args[1].equalsIgnoreCase("version")){

            switch (args[1]){
                case "autor":
                    sender.sendMessage(MessageUtils.getColoredMessage(
                            Welcomer.prefix + plugin.getDescription().getAuthors().toString() +
                                    " &7Decidio desarrollar este &4&lplugin &7para su &c&lNetworck &5&lKimetsuMC"
                    ));
                    break;
                case "version":
                    sender.sendMessage(MessageUtils.getColoredMessage(
                            Welcomer.prefix + "&6 La vercion del plugin es: "
                                   + plugin.getDescription().getVersion()

                    ));
                    break;
            }
        }
        else {
            if(sender.hasPermission("Welcomer.get")){
                sender.sendMessage(MessageUtils.getColoredMessage(
                        Welcomer.prefix +
                                "&6 no tienes permisos suficientes para ejecutar este comando"
                ));
            }
        }
    }
}
