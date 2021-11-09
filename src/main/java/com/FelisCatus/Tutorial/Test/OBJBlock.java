package com.FelisCatus.Tutorial.Test;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class OBJBlock extends Block
{

    public OBJBlock()
    {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5).notSolid());
    }
}
