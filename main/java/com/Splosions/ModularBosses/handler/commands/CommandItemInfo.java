package com.Splosions.ModularBosses.handler.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.math.BlockPos;
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
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "ItemInfo";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "ItemInfo";
	}

	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		if (sender.getCommandSenderEntity() instanceof EntityPlayer){// && sender.getEntityWorld().isRemote
			EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();	
			ItemStack stack = player.getEquipmentInSlot(0);
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Item ModID:ItemName = " + EnumChatFormatting.AQUA + GameRegistry.findUniqueIdentifierFor(stack.getItem()).toString()));
			
		}
		
		
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
