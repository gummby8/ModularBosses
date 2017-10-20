package com.Splosions.ModularBosses.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public class BlockObject {
   
   private BlockPos pos;
   private IBlockState state;
   private String message;
   
   public BlockObject(BlockPos pos, IBlockState state) {
      this.pos = pos;
      this.state = state;

    }

   public BlockPos getPos() {
      return pos;
   }

   public IBlockState getState() {
      return state;
   }
   
      
   public BlockPos getPosWithOffset(int x, int y, int z) {
      return new BlockPos(x + pos.getX(), y + pos.getY(), z + pos.getZ());
   }

}