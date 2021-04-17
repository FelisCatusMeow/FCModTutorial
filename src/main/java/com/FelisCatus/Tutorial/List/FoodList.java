package com.FelisCatus.Tutorial.List;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList {
    public static final Food SOUP = (new Food.Builder().hunger(8).saturation(0.5f)
            .effect(() -> new EffectInstance(Effects.REGENERATION, 100, 2), 1).setAlwaysEdible()).build();
}