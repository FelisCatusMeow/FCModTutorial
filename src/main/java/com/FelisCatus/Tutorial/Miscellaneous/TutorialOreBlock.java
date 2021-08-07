package com.FelisCatus.Tutorial.Miscellaneous;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;
public class TutorialOreBlock extends OreBlock {

	public TutorialOreBlock(Properties properties) {
	    super(properties);
	}
    
    @Override
    protected int getExperience(Random random) 
    {
        return MathHelper.nextInt(random, 2, 6);
    }
}
