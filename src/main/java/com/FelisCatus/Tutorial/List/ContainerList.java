package com.FelisCatus.Tutorial.List;
//这个目录里存放的是container,用于container的注册

import com.FelisCatus.Tutorial.Tutorial;
import com.FelisCatus.Tutorial.Util.Container.GUICaseContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerList
{
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister
            .create(ForgeRegistries.CONTAINERS, Tutorial.MOD_ID);

    public static final RegistryObject<ContainerType<GUICaseContainer>> GUI_CASE_CONTAINER_TYPE = CONTAINERS
            .register("gui_case", () -> IForgeContainerType.create(GUICaseContainer::new));

    //public static final RegistryObject<ContainerType<GeneratorContainer>> GENERATOR_CONTAINER = CONTAINERS.register(
     //       "generator_container",()-> IForgeContainerType.create(GeneratorContainer::new));
}
