package com.Splosions.ModularBosses.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.server.SetControlBlockMessagePacket;
import com.Splosions.ModularBosses.util.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;



@SideOnly(Side.CLIENT)
public class GuiEditControlBlock extends GuiScreen
{
	private final TileEntityControlBlock te;
	private GuiButton btnDone;
	private String message;
	private GuiEditControlBlock.List list;
	
	
	public GuiEditControlBlock(TileEntityControlBlock te) {
		this.te = te;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		btnDone = new GuiButton(0, width / 2 - 100, height / 4 + 120, StatCollector.translateToLocal("gui.done"));
		buttonList.add(btnDone);
		this.list = new GuiEditControlBlock.List(this.mc);
        this.list.registerScrollButtons(7, 8);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		message = this.list.selectedName;
		te.setMessage(message);
		PacketDispatcher.sendToServer(new SetControlBlockMessagePacket(te));
	}
	
    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled && button.id == btnDone.id) {
			te.markDirty();
			mc.displayGuiScreen(null);
		}
		
        if (button.enabled)
        {
            switch (button.id)
            {
                case 5:
                    break;
                case 6:
                   // this.mc.displayGuiScreen(this.parentScreen);
                    break;
                case 100:
                    if (button instanceof GuiOptionButton)
                    {
                        //this.game_settings_3.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                   //     button.displayString = this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
                        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
                        int i = scaledresolution.getScaledWidth();
                        int j = scaledresolution.getScaledHeight();
                        this.setWorldAndResolution(this.mc, i, j);
                    }

                    break;
                default:
                    this.list.actionPerformed(button);
            }
        }
	}

	@Override
	protected void keyTyped(char c, int keyCode) {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			actionPerformed(btnDone);
		} 
		
		/**
		else if (keyCode == Keyboard.KEY_BACK && message.length() > 0) {
			message.deleteCharAt(message.length() - 1);
		} else if (keyCode == Keyboard.KEY_RETURN) {
			message.append("\n");
		} else if (ChatAllowedCharacters.isAllowedCharacter(c) && message.length() < TileEntityControlBlock.MAX_MESSAGE_LENGTH) {
			message.append(c);
		}
		*/
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		/**
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("Monster Spawn Control "), width / 2, 40, 16777215);
		
		String[] lines = StringUtils.wrapString(message.toString(), TileEntityControlBlock.LINE_LENGTH, 5);
		for (int i = 0; i < lines.length; ++i) {
			if (i == 0) {
				lines[i] = "> " + lines[i];
			} else if (i == lines.length - 1) {
				lines[i] += " <";
			}
			fontRendererObj.drawString(lines[i], (width / 2) - (fontRendererObj.getStringWidth(lines[i]) / 2), 80 + i * fontRendererObj.FONT_HEIGHT, 16777215);
		}
		*/
		this.list.drawScreen(mouseX, mouseY, partialTicks);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		
	}
	
	
	

	
	
	
	
	
	
    @SideOnly(Side.CLIENT)
    class List extends GuiSlot
    {
        /** A list containing the many different locale language codes. */
        private final java.util.List nameArray = Lists.newArrayList();
        /** The map containing the Locale-Language pairs. */
        private final Map nameMap = Maps.newHashMap();
        public String selectedName;
        private static final String __OBFID = "CL_00000699";

        public List(Minecraft mcIn)
        {
            super(mcIn, GuiEditControlBlock.this.width, GuiEditControlBlock.this.height, 32, GuiEditControlBlock.this.height - 65 + 4, 18);
            Iterator iterator = EntityList.getEntityNameList().iterator();
            
            while (iterator.hasNext())
            {
            	
                String name = (String) iterator.next();
                Entity entity = EntityList.createEntityByName(name, te.getWorld());
                
                if (entity instanceof EntityLiving && entity != null) {
                this.nameMap.put(name, name);
                this.nameArray.add(name);
                }
            }
        }

        protected int getSize()
        {
            return this.nameArray.size();
        }

        /**
         * The element in the slot that was clicked, boolean for whether it was double clicked or not
         */
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY)
        {
        	System.out.println((this.nameMap.get(this.nameArray.get(slotIndex))).toString());
        	this.selectedName = (this.nameMap.get(this.nameArray.get(slotIndex))).toString();
        }

        /**
         * Returns true if the element passed in is currently selected
         */
        protected boolean isSelected(int slotIndex)
        {
        	if (this.selectedName != null){
            return this.selectedName.equals((this.nameMap.get(this.nameArray.get(slotIndex))).toString());
        	} else {
        		return false;
        	}
        }

        /**
         * Return the height of the content being scrolled
         */
        protected int getContentHeight()
        {
            return this.getSize() * 18;
        }

        protected void drawBackground()
        {
        	GuiEditControlBlock.this.drawDefaultBackground();
        }

        protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_)
        {
        	GuiEditControlBlock.this.fontRendererObj.setBidiFlag(true);
            GuiEditControlBlock.this.drawCenteredString(GuiEditControlBlock.this.fontRendererObj, (this.nameMap.get(this.nameArray.get(entryID))).toString(), this.width / 2, p_180791_3_ + 1, 16777215);
           // GuiEditControlBlock.this.fontRendererObj.setBidiFlag(GuiEditControlBlock.this.languageManager.getCurrentLanguage().isBidirectional());
        }
    }
	
}