package com.Splosions.ModularBosses.handler.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommandItemInfo implements ICommand{

	private final List aliases;
	
    public CommandItemInfo() 
    { 
        aliases = new ArrayList(); 
        aliases.add("MBItemInfo"); 
        aliases.add("mbitem");
        aliases.add("whatisthis");
    } 
	


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "ItemInfo";
	}



	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return aliases;
	}


	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender.getCommandSenderEntity() instanceof EntityPlayer){// && sender.getEntityWorld().isRemote
			EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();	
			ItemStack stack = player.getHeldItemMainhand();
			
			TextComponentString modID = new TextComponentString("Item ModID:ItemName = ");
			modID.getStyle().setColor(TextFormatting.AQUA).setBold(true);
			TextComponentString message = new TextComponentString(stack.getItem().toString());
			message.getStyle().setColor(TextFormatting.GOLD);
			Minecraft.getMinecraft().player.sendMessage(modID.appendSibling(message));
			
			//Minecraft.getMinecraft().playerlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Item ModID:ItemName = " + EnumChatFormatting.AQUA + GameRegistry.findUniqueIdentifierFor(stack.getItem()).toString()));
			
		}
		
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	}

}
