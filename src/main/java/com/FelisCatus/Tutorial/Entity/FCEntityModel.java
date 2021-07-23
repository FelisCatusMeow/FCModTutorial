package com.FelisCatus.Tutorial.Entity;

import com.FelisCatus.Tutorial.List.Entity.FCEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class FCEntityModel<T extends FCEntity> extends EntityModel<T> {
    private final ModelRenderer bb_main;

    public FCEntityModel() {
        textureWidth = 64;
        textureHeight = 64;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main.setTextureOffset(16, 24).addBox(-3.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
        bb_main.setTextureOffset(0, 0).addBox(-5.0F, -9.0F, -7.0F, 10.0F, 9.0F, 14.0F, 0.0F, false);
        bb_main.setTextureOffset(0, 23).addBox(2.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
    }


    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
