package com.FelisCatus.Tutorial.List.Entity;

import com.FelisCatus.Tutorial.Tutorial;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeList {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITIES, Tutorial.MOD_ID);

    public static final RegistryObject<EntityType<FCEntity>> FC_ENTITY = ENTITY_TYPE
            .register("fc_entity", () -> EntityType.Builder
                    .create(FCEntity::new, EntityClassification.MONSTER)
                    .size(1f, 1f)
                    .build(new ResourceLocation(Tutorial.MOD_ID, "fc_entity").toString()));

}
