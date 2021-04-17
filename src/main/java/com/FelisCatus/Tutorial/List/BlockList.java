package com.FelisCatus.Tutorial.List;

import com.FelisCatus.Tutorial.Tutorial;
import com.FelisCatus.Tutorial.Miscellaneous.RotateBlock;
import com.FelisCatus.Tutorial.Miscellaneous.TutorialOreBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockList {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        Tutorial.MOD_ID);
        public static final RegistryObject<Block> TUTORIAL_BLOCK = BLOCKS.register("tutorial_block", () -> new Block(
                        Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.STONE)));

        public static final RegistryObject<Block> TUTORIAL_ORE = BLOCKS.register("tutorial_ore",
                        () -> new TutorialOreBlock(Block.Properties.from(Blocks.IRON_ORE)));

        public static final RegistryObject<RotateBlock> ROTATE_BLOCK = BLOCKS.register("rotate_block",
                        () -> new RotateBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F)
                                        .sound(SoundType.STONE)));

        // public static final RegistryObject<CustomModel> CUSTOM_MODEL =
        // BLOCKS.register("custom_model",()->new
        // CustomModel(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0F)));

        // public static final RegistryObject<Block> TUTORIAL_ORE =
        // BLOCKS.register("tutorial_ore",()->new
        // Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));
        // public static Block tutorial_slab;
}
