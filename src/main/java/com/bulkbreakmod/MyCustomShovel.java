package com.bulkbreakmod;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

public class MyCustomShovel extends ShovelItem {
    public MyCustomShovel(IItemTier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
}
