// C:\Mod\ikkatuMod\src\main\java\com\bulkbreakmod\MyCustomPickaxe.java

package com.bulkbreakmod;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

public class MyCustomPickaxe extends PickaxeItem {
    public MyCustomPickaxe(IItemTier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, (int) attackDamage, attackSpeed, properties);
    }
}
