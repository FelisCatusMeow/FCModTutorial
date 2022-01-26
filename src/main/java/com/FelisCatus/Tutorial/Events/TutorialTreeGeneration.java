package com.FelisCatus.Tutorial.Events;

import com.FelisCatus.Tutorial.World.Gen.TutorialConfigFeature;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class TutorialTreeGeneration
{
    public static void treeGenerate(final BiomeLoadingEvent event) //加载生物群系时触发
    {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, Objects.requireNonNull(event.getName()));

        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        //得到加载生物群系的类型

        //判断
        if (types.contains(BiomeDictionary.Type.PLAINS))
        {
            //得到所有加载群系中配置的feature

            //获得feature的list
            List<Supplier<ConfiguredFeature<?, ?>>> basicList = event.getGeneration().getFeatures
                    (
                            GenerationStage.Decoration.VEGETAL_DECORATION
                    );

            //添加自定义feature

            basicList.add
                    (() -> TutorialConfigFeature.TUTORIALTREE
                                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                                    .withPlacement(Placement.COUNT_EXTRA.configure
                                            (
                                                    new AtSurfaceWithExtraConfig(1, 0.11f, 2)))
                    );


        }


    }
}
