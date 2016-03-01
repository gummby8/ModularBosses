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

	private String message;
	private GuiEditControlBlock.List list;
    private GuiTextField txtXCoord;
    private GuiTextField txtYCoord;
    private GuiTextField txtZCoord;
    private GuiTextField txtSpnFreq;
    private GuiTextField txtSpnDelay;
    private GuiTextField txtSpnCount;
    
	private GuiButton btnDone;
	private GuiButton btnScrollUp;
	private GuiButton btnScrollDn;
	private GuiButton btnRedPwrReq;
	private GuiButton btnRedPwrOut;
	private GuiButton btnRedInstSpn;
    
    String StringtxtXCoord;
    String StringtxtYCoord;
    String StringtxtZCoord;
    int inttxtXCoord;
    int inttxtYCoord;
    int inttxtZCoord;
	
	public GuiEditControlBlock(TileEntityControlBlock te) {
		this.te = te;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		txtXCoord = new GuiTextField(0, this.fontRendererObj, width / 2 + 50, height / 4 - 30, 30, 14);
		txtXCoord.setCanLoseFocus(true);
		//txtXCoord.setText("HHHHHHHHHHHHHHHERP");
		
		txtYCoord = new GuiTextField(0, this.fontRendererObj, width / 2 + 90, height / 4 - 30, 30, 14);
		txtYCoord.setCanLoseFocus(true);
		//txtYCoord.setText("HHHHHHHHHHHHHHHERP");
		
		txtZCoord = new GuiTextField(0, this.fontRendererObj, width / 2 + 130, height / 4 - 30, 30, 14);
		txtZCoord.setCanLoseFocus(true);
		//txtZCoord.setText("HHHHHHHHHHHHHHHERP");

		txtSpnCount = new GuiTextField(0, this.fontRendererObj, width / 2 + 50, height / 4 + 30, 30, 14);
		txtSpnCount.setCanLoseFocus(true);
		//txtXCoord.setText("HHHHHHHHHHHHHHHERP");
		
		txtSpnFreq = new GuiTextField(0, this.fontRendererObj, width / 2 + 90, height / 4 + 30, 30, 14);
		txtSpnFreq.setCanLoseFocus(true);
		//txtYCoord.setText("HHHHHHHHHHHHHHHERP");
		
		txtSpnDelay = new GuiTextField(0, this.fontRendererObj, width / 2 + 130, height / 4 + 30, 30, 14);
		txtSpnDelay.setCanLoseFocus(true);
		//txtZCoord.setText("HHHHHHHHHHHHHHHERP");
		
		btnRedPwrReq = new GuiButton(10, width / 2 + 50, height / 4 + 105, 100 , 20 ,"Redstone");
		buttonList.add(btnRedPwrReq);
		
		btnRedPwrOut = new GuiButton(10, width / 2 + 50, height / 4 + 85, 100 , 20 ,"Redstone");
		buttonList.add(btnRedPwrOut);
		
		btnRedInstSpn = new GuiButton(10, width / 2 + 50, height / 4 + 65, 100 , 20 ,"Redstone");
		buttonList.add(btnRedInstSpn);

		btnDone = new GuiButton(0, width / 2 + 50, height / 4 + 160, 100 , 20 , "Done");
		buttonList.add(btnDone);
		
		this.list = new GuiEditControlBlock.List(this.mc, te.getMessage());
        this.list.registerScrollButtons(7, 8);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);


		StringtxtXCoord = txtXCoord.getText().replaceAll("[^0-9]","");
		if (StringtxtXCoord != null && !StringtxtXCoord.isEmpty()){
		inttxtXCoord = Integer.parseInt(StringtxtXCoord);
		System.out.println(inttxtXCoord);
		}
		
		StringtxtYCoord = txtYCoord.getText().replaceAll("[^0-9]","");
		if (StringtxtYCoord != null && !StringtxtYCoord.isEmpty()){
		inttxtYCoord = Integer.parseInt(StringtxtYCoord);
		System.out.println(inttxtYCoord);
		}
		
		StringtxtZCoord = txtZCoord.getText().replaceAll("[^0-9]","");
		if (StringtxtZCoord != null && !StringtxtZCoord.isEmpty()){
		inttxtZCoord = Integer.parseInt(StringtxtZCoord);
		System.out.println(inttxtZCoord);
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
        txtXCoord.mouseClicked(x, y, button);
        if (button == 1 && x >= txtXCoord.xPosition && x < txtXCoord.xPosition + txtXCoord.width && y >= txtXCoord.yPosition && y < txtXCoord.yPosition + txtXCoord.height) {
        	txtXCoord.setText("");
        }
        
        txtYCoord.mouseClicked(x, y, button);
        if (button == 1 && x >= txtYCoord.xPosition && x < txtYCoord.xPosition + txtYCoord.width && y >= txtYCoord.yPosition && y < txtYCoord.yPosition + txtYCoord.height) {
        	txtYCoord.setText("");
        }
        
        txtZCoord.mouseClicked(x, y, button);
        if (button == 1 && x >= txtZCoord.xPosition && x < txtZCoord.xPosition + txtZCoord.width && y >= txtZCoord.yPosition && y < txtZCoord.yPosition + txtZCoord.height) {
        	txtZCoord.setText("");
        }
        
        txtSpnCount.mouseClicked(x, y, button);
        if (button == 1 && x >= txtSpnCount.xPosition && x < txtSpnCount.xPosition + txtSpnCount.width && y >= txtSpnCount.yPosition && y < txtSpnCount.yPosition + txtSpnCount.height) {
        	txtSpnCount.setText("");
        }
        
        txtSpnFreq.mouseClicked(x, y, button);
        if (button == 1 && x >= txtSpnFreq.xPosition && x < txtSpnFreq.xPosition + txtSpnFreq.width && y >= txtSpnFreq.yPosition && y < txtSpnFreq.yPosition + txtSpnFreq.height) {
        	txtSpnFreq.setText("");
        }
        
        txtSpnDelay.mouseClicked(x, y, button);
        if (button == 1 && x >= txtSpnDelay.xPosition && x < txtSpnDelay.xPosition + txtSpnDelay.width && y >= txtSpnDelay.yPosition && y < txtSpnDelay.yPosition + txtSpnDelay.height) {
        	txtSpnDelay.setText("");
        }
        
        if (button == 0 && x >= btnRedPwrReq.xPosition && x < btnRedPwrReq.xPosition + btnRedPwrReq.width && y >= btnRedPwrReq.yPosition && y < btnRedPwrReq.yPosition + btnRedPwrReq.height) {
        	btnRedPwrReq.enabled = (btnRedPwrReq.enabled == true)? false : true;
        	btnRedPwrReq.displayString = (btnRedPwrReq.enabled == true)? "Off" : "On";
        }
        
        if (button == 0 && x >= btnRedPwrOut.xPosition && x < btnRedPwrOut.xPosition + btnRedPwrOut.width && y >= btnRedPwrOut.yPosition && y < btnRedPwrOut.yPosition + btnRedPwrOut.height) {
        	btnRedPwrOut.enabled = (btnRedPwrOut.enabled == true)? false : true;
        	btnRedPwrOut.displayString = (btnRedPwrOut.enabled == true)? "Off" : "On";
        }
        
        if (button == 0 && x >= btnRedInstSpn.xPosition && x < btnRedInstSpn.xPosition + btnRedInstSpn.width && y >= btnRedInstSpn.yPosition && y < btnRedInstSpn.yPosition + btnRedInstSpn.height) {
        	btnRedInstSpn.enabled = (btnRedInstSpn.enabled == true)? false : true;
        	btnRedInstSpn.displayString = (btnRedInstSpn.enabled == true)? "Off" : "On";
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
		txtXCoord.textboxKeyTyped(c, keyCode);
		txtYCoord.textboxKeyTyped(c, keyCode);
		txtZCoord.textboxKeyTyped(c, keyCode);

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		this.list.drawScreen(mouseX, mouseY, partialTicks);
		
		
		
		this.fontRendererObj.drawString("X Off", width / 2 + 50, height / 4 - 40, 0xFFFFFF);
		this.fontRendererObj.drawString("Y Off", width / 2 + 90, height / 4 - 40, 0xFFFFFF);
		this.fontRendererObj.drawString("Z Off", width / 2 + 130, height / 4 - 40, 0xFFFFFF);
		txtXCoord.drawTextBox();
		txtYCoord.drawTextBox();
		txtZCoord.drawTextBox();
		
		txtSpnCount.drawTextBox();
		txtSpnFreq.drawTextBox();
		txtSpnDelay.drawTextBox();
	
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