package com.FelisCatus.Tutorial.World.Gen;

import com.FelisCatus.Tutorial.List.BlockList;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class TutorialConfigFeature
{
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> TUTORIALTREE = register("oak",
            Feature.TREE.withConfiguration(
                    (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockList.TUTORIAL_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockList.TUTORIAL_LEAVES.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3),
                            new StraightTrunkPlacer(9, 3, 2), //Height, random1, random2
                            new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()));


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

}
