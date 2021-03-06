package com.FelisCatus.Tutorial.List;

import com.FelisCatus.Tutorial.List.TileEntities.GUITileEntity;
import com.FelisCatus.Tutorial.List.TileEntities.GeneratorTileEntity;
import com.FelisCatus.Tutorial.Tutorial;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityList
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister
            .create(ForgeRegistries.TILE_ENTITIES, Tutorial.MOD_ID);

    public static final RegistryObject<TileEntityType<GUITileEntity>> GUI_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
            .register("example_gui", () -> TileEntityType.Builder
                    .create(GUITileEntity::new, BlockList.EXAMPLE_GUI_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<GeneratorTileEntity>> GENERATOR_TILE_ENTITY =
            TILE_ENTITY_TYPE.register("generator_tile_entity", () -> TileEntityType.Builder.create(GeneratorTileEntity::new,
                    BlockList.GENERATOR_BLOCK.get()).build(null));
}
