package com.Splosions.ModularBosses.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.server.SetControlBlockMessagePacket;
import com.Splosions.ModularBosses.util.StringUtils;



@SideOnly(Side.CLIENT)
public class GuiEditControlBlock extends GuiScreen
{
	private final TileEntityControlBlock te;
	private GuiButton btnDone;
	private StringBuilder message;

	public GuiEditControlBlock(TileEntityControlBlock te) {
		this.te = te;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		btnDone = new GuiButton(0, width / 2 - 100, height / 4 + 120, StatCollector.translateToLocal("gui.done"));
		buttonList.add(btnDone);
		message = new StringBuilder(TileEntityControlBlock.MAX_MESSAGE_LENGTH);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		te.setMessage(message.toString());
		PacketDispatcher.sendToServer(new SetControlBlockMessagePacket(te));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled && button.id == btnDone.id) {
			te.markDirty();
			mc.displayGuiScreen(null);
		}
	}

	@Override
	protected void keyTyped(char c, int keyCode) {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			actionPerformed(btnDone);
		} else if (keyCode == Keyboard.KEY_BACK && message.length() > 0) {
			message.deleteCharAt(message.length() - 1);
		} else if (keyCode == Keyboard.KEY_RETURN) {
			message.append("\n");
		} else if (ChatAllowedCharacters.isAllowedCharacter(c) && message.length() < TileEntityControlBlock.MAX_MESSAGE_LENGTH) {
			message.append(c);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("gui.gossip_stone.name"), width / 2, 40, 16777215);
		String[] lines = StringUtils.wrapString(message.toString(), TileEntityControlBlock.LINE_LENGTH, 5);
		for (int i = 0; i < lines.length; ++i) {
			if (i == 0) {
				lines[i] = "> " + lines[i];
			} else if (i == lines.length - 1) {
				lines[i] += " <";
			}
			fontRendererObj.drawString(lines[i], (width / 2) - (fontRendererObj.getStringWidth(lines[i]) / 2), 80 + i * fontRendererObj.FONT_HEIGHT, 16777215);
		}
		super.drawScreen(mouseX, mouseY, f);
	}
}