package com.FelisCatus.Tutorial.List;

import com.FelisCatus.Tutorial.ToolItem.PinkSword;
import com.FelisCatus.Tutorial.Tutorial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemList {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial.MOD_ID);
    public static final RegistryObject<Item> SOUP = ITEMS.register("soup",
            () -> new Item(new Item.Properties().food(FoodList.SOUP).group(Tutorial.TUTORIAL_GROUP)));
    public static final RegistryObject<Item> PINK_SWROD = ITEMS.register("pink_sword", PinkSword::new);

    public static final RegistryObject<Item> WOW = ITEMS.register("wow", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
    
}
