package com.FelisCatus.Tutorial.List;

import com.FelisCatus.Tutorial.Miscellaneous.RotateBlock;
import com.FelisCatus.Tutorial.Miscellaneous.TutorialOreBlock;
import com.FelisCatus.Tutorial.Test.OBJBlock;
import com.FelisCatus.Tutorial.Tutorial;
import com.FelisCatus.Tutorial.Util.CustomBlock;
import com.FelisCatus.Tutorial.Util.SpecialBlocks.ExampleGUIBlock;
import com.FelisCatus.Tutorial.Util.SpecialBlocks.GeneratorBlock;
import com.FelisCatus.Tutorial.World.Gen.TutorialTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockList
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Tutorial.MOD_ID);
    public static final RegistryObject<Block> TUTORIAL_BLOCK = BLOCKS.register("tutorial_block", () -> new Block(
            Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.STONE)));

    public static final RegistryObject<Block> TUTORIAL_ORE = BLOCKS.register("tutorial_ore",
            () -> new TutorialOreBlock(Block.Properties.from(Blocks.IRON_ORE)));

    public static final RegistryObject<RotateBlock> ROTATE_BLOCK = BLOCKS.register("rotate_block",
            () -> new RotateBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<CustomBlock> CUSTOM_BLOCK = BLOCKS.register("custom_block",
            () -> new CustomBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F)));

    public static final RegistryObject<Block> EXAMPLE_GUI_BLOCK = BLOCKS.register("example_gui_block",
            () -> new ExampleGUIBlock());

    public static final RegistryObject<Block> GENERATOR_BLOCK = BLOCKS.register(
            "generator_block", () -> new GeneratorBlock());

    public static final RegistryObject<Block> OBJ_BLOCK = BLOCKS.register("obj_block", OBJBlock::new);


    public static final RegistryObject<Block> TUTORIAL_LOG = BLOCKS.register("tutorial_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> TUTORIAL_LEAVES = BLOCKS.register("tutorial_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f)
                    .tickRandomly().sound(SoundType.PLANT).notSolid()));

    public static final RegistryObject<Block> TUTORIAL_SAPLING = BLOCKS.register("tutorial_sapling",
            () -> new SaplingBlock(new TutorialTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));




    /*
     * //??????
     *
     * @SuppressWarnings("deprecation") public static final
     * RegistryObject<StairsBlock> CUSTOM_STAIR =
     * BLOCKS.register("custom_stair",()-> new
     * StairsBlock(Blocks.STONE_STAIRS.getDefaultState(),Block.Properties.from(
     * Blocks.STONE_STAIRS)));
     *
     * //?????? public static final RegistryObject<WallBlock> CUSTOM_WALL =
     * BLOCKS.register("custom_wall",()-> new
     * WallBlock(Block.Properties.from(Blocks.COBBLESTONE_WALL)));
     */

    // public static final RegistryObject<CustomModel> CUSTOM_MODEL =
    // BLOCKS.register("custom_model",()->new
    // CustomModel(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0F)));

    // public static final RegistryObject<Block> TUTORIAL_ORE =
    // BLOCKS.register("tutorial_ore",()->new
    // Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));
    // public static Block tutorial_slab;
}
