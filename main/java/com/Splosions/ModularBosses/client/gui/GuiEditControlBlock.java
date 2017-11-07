package com.Splosions.ModularBosses.client.gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.server.SetControlBlockMessagePacket;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEditControlBlock extends GuiScreen {

	private final TileEntityControlBlock te;

	private String message;
	private GuiEditControlBlock.List list;

	private GuiTextField txtSpnFreq;
	// private GuiTextField txtSpnDelay;
	private GuiTextField txtSpnCount;
	private String StringtxtSpnFreq;
	// private String StringtxtSpnDelay;
	private String StringtxtSpnCount;
	private int inttxtSpnFreq = 1;
	// private int inttxtSpnDelay;
	private int inttxtSpnCount = 1;

	private GuiButton btnDone;
	private GuiButton btnScrollUp;
	private GuiButton btnScrollDn;
	private GuiButton btnRandomSpnLoc;
	private GuiButton btnBorder;
	private int ranspwn = 0;
	private int border = 0;

	private GuiTextField txtXCoord;
	private GuiTextField txtYCoord;
	private GuiTextField txtZCoord;
	private String StringtxtXCoord;
	private String StringtxtYCoord;
	private String StringtxtZCoord;
	int inttxtXCoord = 10;
	int inttxtYCoord = 10;
	int inttxtZCoord = 10;

	public GuiEditControlBlock(TileEntityControlBlock te) {
		this.te = te;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		System.out.println(this.te.getMessage());
		System.out.println("DDDDDDDDDDDDDDERP");
		String[] mesArray = this.te.getMessage().split("\\|");
		if (mesArray.length >= 8) {
			inttxtXCoord = Integer.parseInt(mesArray[1]);
			inttxtYCoord = Integer.parseInt(mesArray[2]);
			inttxtZCoord = Integer.parseInt(mesArray[3]);
			inttxtSpnFreq = Integer.parseInt(mesArray[4]);
			inttxtSpnCount = Integer.parseInt(mesArray[5]);
			ranspwn = Integer.parseInt(mesArray[6]);
			border = Integer.parseInt(mesArray[7]);


		}

		txtXCoord = new GuiTextField(0, this.fontRenderer, width / 2 + 50, height / 4 - 30, 30, 14);
		txtXCoord.setCanLoseFocus(true);
		txtXCoord.setText(inttxtXCoord + "");

		txtYCoord = new GuiTextField(0, this.fontRenderer, width / 2 + 90, height / 4 - 30, 30, 14);
		txtYCoord.setCanLoseFocus(true);
		txtYCoord.setText(inttxtYCoord + "");

		txtZCoord = new GuiTextField(0, this.fontRenderer, width / 2 + 130, height / 4 - 30, 30, 14);
		txtZCoord.setCanLoseFocus(true);
		txtZCoord.setText(inttxtZCoord + "");

		txtSpnFreq = new GuiTextField(0, this.fontRenderer, width / 2 + 75, height / 4 + 10, 60, 14);
		txtSpnFreq.setCanLoseFocus(true);
		txtSpnFreq.setText(inttxtSpnFreq + "");

		txtSpnCount = new GuiTextField(0, this.fontRenderer, width / 2 + 75, height / 4 + 50, 60, 14);
		txtSpnCount.setCanLoseFocus(true);
		txtSpnCount.setText(inttxtSpnCount + "");

		btnRandomSpnLoc = new GuiButton(11, width / 2 + 50, height / 4 + 130, 100, 20, "Ran Spawn Loc");
		buttonList.add(btnRandomSpnLoc);

		btnBorder = new GuiButton(10, width / 2 + 180, height / 4 + 160, 45, 20, "Border");
		buttonList.add(btnBorder);

		btnDone = new GuiButton(0, width / 2 + 50, height / 4 + 160, 100, 20, "Done");
		buttonList.add(btnDone);
		
		btnRandomSpnLoc.displayString = (ranspwn == 0) ? "Ran Spawn Loc" : "On";
		btnRandomSpnLoc.enabled = (ranspwn == 1) ? false : true;
		
		btnBorder.displayString = (border == 0) ? "Border" : "On";
		btnBorder.enabled = (border == 1) ? false : true;
		
		

		this.list = new GuiEditControlBlock.List(this.mc, te.getMessage());
		this.list.registerScrollButtons(7, 8);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);

		StringtxtXCoord = txtXCoord.getText().replaceAll("[^0-9]", "");
		if (StringtxtXCoord != null && !StringtxtXCoord.isEmpty() && StringtxtXCoord != "") {
			inttxtXCoord = Integer.parseInt(StringtxtXCoord);
			// System.out.println(inttxtXCoord);
		}

		StringtxtYCoord = txtYCoord.getText().replaceAll("[^0-9]", "");
		if (StringtxtYCoord != null && !StringtxtYCoord.isEmpty() && StringtxtYCoord != "") {
			inttxtYCoord = Integer.parseInt(StringtxtYCoord);
			// System.out.println(inttxtYCoord);
		}

		StringtxtZCoord = txtZCoord.getText().replaceAll("[^0-9]", "");
		if (StringtxtZCoord != null && !StringtxtZCoord.isEmpty() && StringtxtZCoord != "") {
			inttxtZCoord = Integer.parseInt(StringtxtZCoord);
			// System.out.println(inttxtZCoord);
		}

		StringtxtSpnFreq = txtSpnFreq.getText().replaceAll("[^0-9]", "");
		if (StringtxtSpnFreq != null && !StringtxtSpnFreq.isEmpty() && StringtxtSpnFreq != "") {
			inttxtSpnFreq = Integer.parseInt(StringtxtSpnFreq);
			// System.out.println(inttxtSpnFreq);
		}

		StringtxtSpnCount = txtSpnCount.getText().replaceAll("[^0-9]", "");
		if (StringtxtSpnCount != null && !StringtxtSpnCount.isEmpty() && StringtxtSpnCount != "") {
			inttxtSpnCount = Integer.parseInt(StringtxtSpnCount);
			// System.out.println(inttxtSpnCount);
		}

		ranspwn = (btnRandomSpnLoc.enabled) ? 0 : 1;
		border = (btnBorder.enabled) ? 0 : 1;

		message = this.list.selectedName + "|" + inttxtXCoord + "|" + inttxtYCoord + "|" + inttxtZCoord + "|" + inttxtSpnFreq + "|" + inttxtSpnCount + "|" + ranspwn + "|" + border;
		System.out.println("GUI closed message = " + message);
		if (message != null && message != "") {
			te.setMessage(message);
			PacketDispatcher.sendToServer(new SetControlBlockMessagePacket(te));
		}
	}

	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	@Override
	protected void mouseClicked(int x, int y, int button) throws IOException {
		super.mouseClicked(x, y, button);
		txtXCoord.mouseClicked(x, y, button);
		if (button == 1 && x >= txtXCoord.x && x < txtXCoord.x + txtXCoord.width && y >= txtXCoord.y && y < txtXCoord.y + txtXCoord.height) {
			txtXCoord.setText("");
		}

		txtYCoord.mouseClicked(x, y, button);
		if (button == 1 && x >= txtYCoord.x && x < txtYCoord.x + txtYCoord.width && y >= txtYCoord.y && y < txtYCoord.y + txtYCoord.height) {
			txtYCoord.setText("");
		}

		txtZCoord.mouseClicked(x, y, button);
		if (button == 1 && x >= txtZCoord.x && x < txtZCoord.x + txtZCoord.width && y >= txtZCoord.y && y < txtZCoord.y + txtZCoord.height) {
			txtZCoord.setText("");
		}

		txtSpnCount.mouseClicked(x, y, button);
		if (button == 1 && x >= txtSpnCount.x && x < txtSpnCount.x + txtSpnCount.width && y >= txtSpnCount.y && y < txtSpnCount.y + txtSpnCount.height) {
			txtSpnCount.setText("");
		}

		txtSpnFreq.mouseClicked(x, y, button);
		if (button == 1 && x >= txtSpnFreq.x && x < txtSpnFreq.x + txtSpnFreq.width && y >= txtSpnFreq.y && y < txtSpnFreq.y + txtSpnFreq.height) {
			txtSpnFreq.setText("");
		}

		if (button == 0 && x >= btnRandomSpnLoc.x && x < btnRandomSpnLoc.x + btnRandomSpnLoc.width && y >= btnRandomSpnLoc.y && y < btnRandomSpnLoc.y + btnRandomSpnLoc.height) {
			btnRandomSpnLoc.enabled = (btnRandomSpnLoc.enabled == true) ? false : true;
			btnRandomSpnLoc.displayString = (btnRandomSpnLoc.enabled == true) ? "Ran Spawn Loc" : "On";
		}

		if (button == 0 && x >= btnBorder.x && x < btnBorder.x + btnBorder.width && y >= btnBorder.y && y < btnBorder.y + btnBorder.height) {
			btnBorder.enabled = (btnBorder.enabled == true) ? false : true;
			btnBorder.displayString = (btnBorder.enabled == true) ? "Border" : "On";
		}

	}

	/**
	 * Handles mouse input.
	 */
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.list.handleMouseInput();
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled && button.id == btnDone.id) {
			te.markDirty();
			mc.displayGuiScreen(null);
		}

		if (button.enabled) {
			switch (button.id) {
			case 5:
				break;
			case 6:
				// this.mc.displayGuiScreen(this.parentScreen);
				break;
			case 100:
				if (button instanceof GuiOptionButton) {
					// this.game_settings_3.setOptionValue(((GuiOptionButton)button).returnEnumOptions(),
					// 1);
					// button.displayString =
					// this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
					ScaledResolution scaledresolution = new ScaledResolution(this.mc);
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
	protected void keyTyped(char c, int keyCode) throws IOException {
		super.keyTyped(c, keyCode);
		if (keyCode == Keyboard.KEY_ESCAPE) {
			actionPerformed(btnDone);

		}
		txtXCoord.textboxKeyTyped(c, keyCode);
		txtYCoord.textboxKeyTyped(c, keyCode);
		txtZCoord.textboxKeyTyped(c, keyCode);

		txtSpnFreq.textboxKeyTyped(c, keyCode);
		txtSpnCount.textboxKeyTyped(c, keyCode);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		this.list.drawScreen(mouseX, mouseY, partialTicks);

		this.fontRenderer.drawString("X Off", width / 2 + 50, height / 4 - 40, 0xFFFFFF);
		this.fontRenderer.drawString("Y Off", width / 2 + 90, height / 4 - 40, 0xFFFFFF);
		this.fontRenderer.drawString("Z Off", width / 2 + 130, height / 4 - 40, 0xFFFFFF);

		// this.fontRendererObj.drawString("Spawn Delay", width / 2 + 75, height
		// / 4 + 0, 0xFFFFFF);
		this.fontRenderer.drawString("Spawn Frequency", width / 2 + 60, height / 4 + 0, 0xFFFFFF);
		this.fontRenderer.drawString("Spawn Count", width / 2 + 75, height / 4 + 40, 0xFFFFFF);

		txtXCoord.drawTextBox();
		txtYCoord.drawTextBox();
		txtZCoord.drawTextBox();

		txtSpnCount.drawTextBox();
		txtSpnFreq.drawTextBox();
		// txtSpnDelay.drawTextBox();

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@SideOnly(Side.CLIENT)
	class List extends GuiSlot {
		/** A list containing the many different locale language codes. */
		private final java.util.List nameArray = Lists.newArrayList();
		/** The map containing the Locale-Language pairs. */
		private final Map nameMap = Maps.newHashMap();
		public String selectedName;
		private static final String __OBFID = "CL_00000699";

		public List(Minecraft mcIn, String message) {
			super(mcIn, GuiEditControlBlock.this.width / 2, GuiEditControlBlock.this.height, 0, GuiEditControlBlock.this.height, 18);

			String[] mesArray = message.split("\\|", -1);

			this.selectedName = mesArray[0];

			Iterator iterator = EntityList.getEntityNameList().iterator();

			while (iterator.hasNext()) {
				String name = (String) iterator.next();
				if (name != "ThrownEnderpearl") {
					Entity entity = EntityList.createEntityByIDFromName(new ResourceLocation(name), te.getWorld());
					// System.out.println(entity);
					if (entity != null && entity instanceof EntityLiving) {
						this.nameMap.put(name, name);
						this.nameArray.add(name);
					}
				}
			}
			java.util.Collections.sort(this.nameArray);
		}

		@Override
		protected void overlayBackground(int p_148136_1_, int p_148136_2_, int p_148136_3_, int p_148136_4_) {

		}

		@Override
		protected void drawContainerBackground(Tessellator tessellator) {

		}

		protected int getSize() {
			return this.nameArray.size();
		}

		/**
		 * The element in the slot that was clicked, boolean for whether it was
		 * double clicked or not
		 */
		protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {

			this.selectedName = (this.nameMap.get(this.nameArray.get(slotIndex))).toString();
		}

		/**
		 * Returns true if the element passed in is currently selected
		 */
		protected boolean isSelected(int slotIndex) {

			if (this.selectedName != null) {
				return this.selectedName.equals((this.nameMap.get(this.nameArray.get(slotIndex))).toString());
			} else {
				return false;
			}
		}

		/**
		 * Return the height of the content being scrolled
		 */
		protected int getContentHeight() {
			return this.getSize() * 18;
		}

		@Override
		protected void drawBackground() {
			GuiEditControlBlock.this.drawDefaultBackground();
		}

		protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
			// GuiEditControlBlock.this.fontRendererObj.setBidiFlag(true);
			GuiEditControlBlock.this.drawCenteredString(GuiEditControlBlock.this.fontRenderer, (this.nameMap.get(this.nameArray.get(entryID))).toString(), this.width / 2, p_180791_3_ + 3, 16777215);
			// GuiEditControlBlock.this.fontRendererObj.setBidiFlag(GuiEditControlBlock.this.languageManager.getCurrentLanguage().isBidirectional());
		}

		@Override
		protected void drawSlot(int p_192637_1_, int p_192637_2_, int p_192637_3_, int p_192637_4_, int p_192637_5_,
				int p_192637_6_, float p_192637_7_) {
			// TODO Auto-generated method stub
			
		}
	}

}