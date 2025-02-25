package net.nuggetmc.tplus.command.commands;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.nuggetmc.tplus.TerminatorPlus;
import net.nuggetmc.tplus.api.utils.ChatUtils;
import net.nuggetmc.tplus.command.CommandHandler;
import net.nuggetmc.tplus.command.CommandInstance;
import net.nuggetmc.tplus.command.annotation.Command;
import net.nuggetmc.tplus.utils.MCLogs;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class MainCommand extends CommandInstance {

    private BaseComponent[] rootInfo;

    public MainCommand(CommandHandler handler, String name, String description, String... aliases) {
        super(handler, name, description, aliases);
    }

    @Command
    public void root(CommandSender sender) {
        if (rootInfo == null) {
            rootInfoSetup();
        }

        sender.spigot().sendMessage(rootInfo);
    }
    @Command(
            name = "debuginfo",
            desc = "Upload debug info to mclo.gs"
    )
    public void debugInfo(CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + "Uploading debug info to mclogs...");
        String url = null;
        try {
            url = MCLogs.postInfo();
        } catch (IOException e) {
            String error = e.getMessage();
            sender.sendMessage(ChatColor.RED + "Failed to upload debug info to mclogs: " + error);
            return;
        }
        sender.sendMessage(ChatColor.GREEN + "Debug info uploaded to " + url);
    }


    private void rootInfoSetup() {
        ComponentBuilder message = new ComponentBuilder();
        String pluginName = TerminatorPlus.getInstance().getName();

        message.append(ChatUtils.LINE + "\n");
        message.append(ChatColor.GOLD + pluginName + ChatColor.GRAY + " [v" + TerminatorPlus.getVersion() + "]\n");
        message.append("\nPlugin Information:\n");
        message.append(ChatUtils.BULLET_FORMATTED + "Author" + ChatUtils.BULLET_FORMATTED + ChatColor.YELLOW + "HorseNuggets\n");
        message.append(ChatUtils.BULLET_FORMATTED + "Links" + ChatUtils.BULLET_FORMATTED);
        message.append(ChatColor.RED + "YouTube");
        message.event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://youtube.com/horsenuggets"));
        message.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to visit HorseNuggets' " + ChatColor.RED + "YouTube" + ChatColor.RESET + "!")));
        message.append(", ");
        message.event((ClickEvent) null);
        message.event((HoverEvent) null);
        message.append(ChatColor.BLUE + "Discord");
        message.event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/vZVSf2D6mz"));
        message.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to visit HorseNuggets' " + ChatColor.BLUE + "Discord" + ChatColor.RESET + "!")));
        message.append("\n");
        message.event((ClickEvent) null);
        message.event((HoverEvent) null);
        message.append("\nPlugin Commands:\n");

        commandHandler.getCommands().forEach((name, command) -> {
            if (!name.equalsIgnoreCase(pluginName)) {
                message.append(ChatUtils.BULLET_FORMATTED + ChatColor.YELLOW + "/" + name + ChatUtils.BULLET_FORMATTED + command.getDescription() + "\n");
            }
        });

        message.append(ChatUtils.LINE);

        this.rootInfo = message.create();
    }
}
