package com.Splosions.ModularBosses.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
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
import java.util.Collections;
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
	private GuiButton btnScrollUp;
	private GuiButton btnScrollDn;
	private GuiButton btnRedstone;
	private String message;
	private GuiEditControlBlock.List list;
    private GuiTextField xCoord;
    private GuiTextField yCoord;
    private GuiTextField zCoord;
    String StringXCoord;
    String StringYCoord;
    String StringZCoord;
    int intXCoord;
    int intYCoord;
    int intZCoord;
	
	public GuiEditControlBlock(TileEntityControlBlock te) {
		this.te = te;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		xCoord = new GuiTextField(0, this.fontRendererObj, width / 2 + 50, height / 4 + 30, 30, 14);
		xCoord.setCanLoseFocus(true);
		//xCoord.setText("HHHHHHHHHHHHHHHERP");
		
		yCoord = new GuiTextField(0, this.fontRendererObj, width / 2 + 90, height / 4 + 30, 30, 14);
		yCoord.setCanLoseFocus(true);
		//yCoord.setText("HHHHHHHHHHHHHHHERP");
		
		zCoord = new GuiTextField(0, this.fontRendererObj, width / 2 + 130, height / 4 + 30, 30, 14);
		zCoord.setCanLoseFocus(true);
		//zCoord.setText("HHHHHHHHHHHHHHHERP");
		
		btnDone = new GuiButton(0, width / 2 + 50, height / 4 + 160, 100 , 20 , "Done");
		buttonList.add(btnDone);

		btnRedstone = new GuiButton(10, width / 2 + 50, height / 4 + 135, 100 , 20 ,"Redstone");
		buttonList.add(btnRedstone);
		
		this.list = new GuiEditControlBlock.List(this.mc, te.getMessage());
        this.list.registerScrollButtons(7, 8);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);


		StringXCoord = xCoord.getText().replaceAll("[^0-9]","");
		if (StringXCoord != null && !StringXCoord.isEmpty()){
		intXCoord = Integer.parseInt(StringXCoord);
		System.out.println(intXCoord);
		}
		
		StringYCoord = yCoord.getText().replaceAll("[^0-9]","");
		if (StringYCoord != null && !StringYCoord.isEmpty()){
		intYCoord = Integer.parseInt(StringYCoord);
		System.out.println(intYCoord);
		}
		
		StringZCoord = zCoord.getText().replaceAll("[^0-9]","");
		if (StringZCoord != null && !StringZCoord.isEmpty()){
		intZCoord = Integer.parseInt(StringZCoord);
		System.out.println(intZCoord);
		}
		
		message = this.list.selectedName;
		if (message != null || message != ""){
			te.setMessage(message);
			PacketDispatcher.sendToServer(new SetControlBlockMessagePacket(te));
		}
	}
	
    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException
    {
        super.mouseClicked(x, y, button);
        xCoord.mouseClicked(x, y, button);
        if (button == 1 && x >= xCoord.xPosition && x < xCoord.xPosition + xCoord.width && y >= xCoord.yPosition && y < xCoord.yPosition + xCoord.height) {
        	xCoord.setText("");
        }
        
        yCoord.mouseClicked(x, y, button);
        if (button == 1 && x >= yCoord.xPosition && x < yCoord.xPosition + yCoord.width && y >= yCoord.yPosition && y < yCoord.yPosition + yCoord.height) {
        	yCoord.setText("");
        }
        
        zCoord.mouseClicked(x, y, button);
        if (button == 1 && x >= zCoord.xPosition && x < zCoord.xPosition + zCoord.width && y >= zCoord.yPosition && y < zCoord.yPosition + zCoord.height) {
        	zCoord.setText("");
        }
        
        if (button == 0 && x >= btnRedstone.xPosition && x < btnRedstone.xPosition + btnRedstone.width && y >= btnRedstone.yPosition && y < btnRedstone.yPosition + btnRedstone.height) {
        	btnRedstone.enabled = (btnRedstone.enabled == true)? false : true;
        	btnRedstone.displayString = (btnRedstone.enabled == true)? "Off" : "On";
        }
        
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
	protected void keyTyped(char c, int keyCode) throws IOException{
		super.keyTyped(c, keyCode);
		if (keyCode == Keyboard.KEY_ESCAPE) {
			actionPerformed(btnDone);
			
			
	       
		} 
		xCoord.textboxKeyTyped(c, keyCode);
		yCoord.textboxKeyTyped(c, keyCode);
		zCoord.textboxKeyTyped(c, keyCode);

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		this.list.drawScreen(mouseX, mouseY, partialTicks);
		
		
		
		this.fontRendererObj.drawString("X Off", width / 2 + 50, height / 4 + 20, 0xFFFFFF);
		this.fontRendererObj.drawString("Y Off", width / 2 + 90, height / 4 + 20, 0xFFFFFF);
		this.fontRendererObj.drawString("Z Off", width / 2 + 130, height / 4 + 20, 0xFFFFFF);
		xCoord.drawTextBox();
		yCoord.drawTextBox();
		zCoord.drawTextBox();
	
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
        

        public List(Minecraft mcIn, String preSetName)
        {
            super(mcIn, GuiEditControlBlock.this.width / 2, GuiEditControlBlock.this.height, 0, GuiEditControlBlock.this.height, 18);

            this.selectedName = preSetName;
            
            Iterator iterator = EntityList.getEntityNameList().iterator();
            
            while (iterator.hasNext())
            {
                String name = (String) iterator.next();
                if (name != "ThrownEnderpearl"){
                	Entity entity = EntityList.createEntityByName(name, te.getWorld());
                	//System.out.println(entity);
                	if (entity instanceof EntityLiving && entity != null) {
                		this.nameMap.put(name, name);
                		this.nameArray.add(name);
                	}
                }
            }
            java.util.Collections.sort(this.nameArray);
        }
        
        @Override
        protected void overlayBackground(int p_148136_1_, int p_148136_2_, int p_148136_3_, int p_148136_4_)
        {

        }
        
        @Override
        protected void drawContainerBackground(Tessellator tessellator)
        {

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

        @Override
        protected void drawBackground()
        {
        	GuiEditControlBlock.this.drawDefaultBackground();
        }

        protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_)
        {
        	//GuiEditControlBlock.this.fontRendererObj.setBidiFlag(true);
            GuiEditControlBlock.this.drawCenteredString(GuiEditControlBlock.this.fontRendererObj, (this.nameMap.get(this.nameArray.get(entryID))).toString(), this.width / 2, p_180791_3_ + 3, 16777215);
           // GuiEditControlBlock.this.fontRendererObj.setBidiFlag(GuiEditControlBlock.this.languageManager.getCurrentLanguage().isBidirectional());
        }
    }
	
}