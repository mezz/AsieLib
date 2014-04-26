package pl.asie.lib.chat;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import pl.asie.lib.AsieLibMod;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;

public class CommandNick extends CommandBase
{
	public String getCommandName()
    {
        return "nick";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "commands.nick.usage";
    }

    public void processCommand(ICommandSender sender, String[] args)
    {
        if (args.length > 0 && args[0].length() > 0) {
        	String target = args[0];
        	String newName = target;
        	if(args.length == 2) newName = args[1];
        	else target = sender.getCommandSenderName();
        	
        	AsieLibMod.nick.setNickname(target, newName);
        	ChatMessageComponent cmc = new ChatMessageComponent();
        	cmc.addKey("commands.nick.done");
        	sender.sendChatToPlayer(cmc);
        } else throw new WrongUsageException("commands.nick.usage", new Object[0]);
    }
    
    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender sender, String[] args)
    {
        return args.length >= 1 ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
    }
}