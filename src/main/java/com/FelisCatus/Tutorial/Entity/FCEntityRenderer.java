package com.FelisCatus.Tutorial.Entity;

import com.FelisCatus.Tutorial.List.Entity.FCEntity;
import com.FelisCatus.Tutorial.Tutorial;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FCEntityRenderer extends MobRenderer<FCEntity, FCEntityModel<FCEntity>> {

    public static final ResourceLocation ENTITY_LOCATION = new ResourceLocation(Tutorial.MOD_ID, "textures/entity/fc_entity.png");

    public FCEntityRenderer(EntityRendererManager manager) {
        super(manager, new FCEntityModel<>(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(FCEntity entity) {
        return ENTITY_LOCATION;
    }
}
