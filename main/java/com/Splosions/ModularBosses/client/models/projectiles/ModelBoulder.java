package com.Splosions.ModularBosses.client.models.projectiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBoulder extends ModelBase {

	public ModelRenderer part;

	public ModelBoulder() {
		textureWidth = 16;
		textureHeight = 16;

		this.part = new ModelRenderer(this, 0, 0);
		this.part.setRotationPoint(0F, 0, 0F);
		this.part.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		part.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {

	}
}
