package com.FelisCatus.Tutorial.List.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class FCEntity extends MobEntity {

    protected FCEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20f)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1f)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.4f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.4f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6f));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public boolean attackable() {
        return true;
    }


    @Override
    public boolean canAttack(EntityType<?> typeIn) {
        return true;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 10;
    }
}
