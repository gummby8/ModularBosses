
package com.Splosions.ModularBosses.blocks;

import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.blocks.BlockRotationData.Rotation;
import com.Splosions.ModularBosses.world.PortalLandingWorldData;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockPortalLanding extends Block implements IVanillaRotation
{

		
	public BlockPortalLanding(Material material) {
		super(material);
		setHardness(10.0F);
		setHarvestLevel("pickaxe", 2);
		setStepSound(soundTypeStone);
		setCreativeTab(MBCreativeTabs.tabBlocks);
		
	}
	

	@Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		PortalLandingWorldData roomData = (PortalLandingWorldData) worldIn.getPerWorldStorage().loadData(PortalLandingWorldData.class, "lobbyPortals");
		if (roomData == null){
			System.out.println("No LobbyPortals Tag found, creating one");
			roomData = new PortalLandingWorldData("lobbyPortals");
			worldIn.getPerWorldStorage().setData("lobbyPortals", roomData);
		}		
    	roomData.addPortalLanding(0, pos.getX(), pos.getY(), pos.getZ());
    	roomData.markDirty();
		
		
        return this.getStateFromMeta(meta);
    }

	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		PortalLandingWorldData roomData = (PortalLandingWorldData) worldIn.getPerWorldStorage().loadData(PortalLandingWorldData.class, "lobbyPortals");		
		roomData.deletePortalLanding(0, pos.getX(), pos.getY(), pos.getZ());
	}


	@Override
	public Rotation getRotationPattern() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}