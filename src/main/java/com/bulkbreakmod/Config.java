// C:\Mod\ikkatuMod\src\main\java\com\bulkbreakmod\Config.java

package com.bulkbreakmod;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static ForgeConfigSpec CONFIG;

    public static ForgeConfigSpec.IntValue MAX_BREAK_LIMIT;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("General");
        MAX_BREAK_LIMIT = builder.comment("最大一括破壊数")
                .defineInRange("maxBreakLimit", 10, 1, 100);
        builder.pop();

        CONFIG = builder.build();
    }
}
