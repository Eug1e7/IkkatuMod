package com.bulkbreakmod;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class MyCustomAxe extends AxeItem {
    public MyCustomAxe(IItemTier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
}
