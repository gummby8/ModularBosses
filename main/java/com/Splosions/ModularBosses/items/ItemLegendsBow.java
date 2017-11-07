package com.Splosions.ModularBosses.items;

import java.util.Collection;
import java.util.List;

import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.client.ISwapModel;
import com.Splosions.ModularBosses.client.render.items.RenderItemLegendsBow;
import com.Splosions.ModularBosses.entity.projectile.EntityEnergyArrow;
import com.google.common.collect.Lists;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ItemLegendsBow extends BaseModItem implements ISwapModel {

	public int aniCount = 0;
	
	public ItemLegendsBow(ToolMaterial material) {
		setCreativeTab(MBCreativeTabs.tabTools);
		setMaxStackSize(1);
	}

	
	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        this.aniCount = 1000;
        itemStackIn.animationsToGo = this.aniCount; 
        return itemStackIn;
	}
	
	 /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     *  
     * @param timeLeft The amount of ticks left before the using would have been complete
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
    {
    	stack.setAnimationsToGo(this.aniCount = 0);
        int j = this.getMaxItemUseDuration(stack) - timeLeft;
        net.minecraftforge.event.entity.player.ArrowLooseEvent event = new net.minecraftforge.event.entity.player.ArrowLooseEvent(playerIn, stack, worldIn, j);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return;
        j = event.getCharge();

        boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (flag || playerIn.inventory.hasItem(Items.arrow))
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(worldIn, playerIn, f * 2.0F);

            entityarrow.setIsCritical(true);

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
            {
                entityarrow.setFire(100);
            }


            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                playerIn.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!worldIn.isRemote)
            {
            	if (f == 1.0F){
            		EntityEnergyArrow entityEnergyArrow = new EntityEnergyArrow(worldIn, playerIn, playerIn, 3F, 0, 0, 0, 0,1,1,1);
            		worldIn.spawnEntityInWorld(entityEnergyArrow);
            		worldIn.playSoundAtEntity(playerIn,"random.fizz",1,1);
            	} else {
            	worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                worldIn.spawnEntityInWorld(entityarrow);
                
            	}
            }
        }
    }
	
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
    	
        return 72000;
    }
    
    

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public Collection<ModelResourceLocation> getDefaultResources() {
		List<ModelResourceLocation> resources = Lists.newArrayList();
		resources.add(new ModelResourceLocation("mb:Legends_Bow", "inventory"));

		return resources;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends IBakedModel> getNewModel() {
		return RenderItemLegendsBow.class;
	}

}
