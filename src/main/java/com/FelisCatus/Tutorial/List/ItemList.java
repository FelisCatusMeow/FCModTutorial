package com.FelisCatus.Tutorial.List;

import com.FelisCatus.Tutorial.ToolItem.PinkSword;
import com.FelisCatus.Tutorial.Tutorial;
import com.FelisCatus.Tutorial.Util.TutorialArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemList
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial.MOD_ID);
    public static final RegistryObject<Item> SOUP = ITEMS.register("soup",
            () -> new Item(new Item.Properties().food(FoodList.SOUP).group(Tutorial.TUTORIAL_GROUP)));
    public static final RegistryObject<Item> PINK_SWROD = ITEMS.register("pink_sword", PinkSword::new);

    public static final RegistryObject<Item> WOW = ITEMS.register("wow",
            () -> new Item(new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> CHROME_BOOTS = ITEMS.register("chrome_boots",
            () -> new ArmorItem(TutorialArmorMaterial.CHROME, EquipmentSlotType.FEET,
                    new Item.Properties().group(Tutorial.TUTORIAL_GROUP)));
    public static final RegistryObject<Item> CHROME_HELMET = ITEMS.register("chrome_helmet",
            () -> new ArmorItem(TutorialArmorMaterial.CHROME, EquipmentSlotType.HEAD,
                    new Item.Properties().group(Tutorial.TUTORIAL_GROUP)));
    public static final RegistryObject<Item> CHROME_LEGGINGS = ITEMS.register("chrome_leggings",
            () -> new ArmorItem(TutorialArmorMaterial.CHROME, EquipmentSlotType.LEGS,
                    new Item.Properties().group(Tutorial.TUTORIAL_GROUP)));
    public static final RegistryObject<Item> CHROME_CHESTPLATE = ITEMS.register("chrome_chestplate",
            () -> new ArmorItem(TutorialArmorMaterial.CHROME, EquipmentSlotType.CHEST,
                    new Item.Properties().group(Tutorial.TUTORIAL_GROUP)));
}
