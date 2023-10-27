// C:\Mod\ikkatuMod\src\main\java\com\bulkbreakmod\ToolBlockMapping.java

package com.bulkbreakmod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ToolBlockMapping {
    public static final Map<Item, Set<Block>> toolToBlocks = new HashMap<>();

    static {
        // ピッケルで破壊可能なブロック
        Set<Block> pickaxeBlocks = new HashSet<>();
        pickaxeBlocks.add(Blocks.STONE);
        pickaxeBlocks.add(Blocks.COBBLESTONE);
        pickaxeBlocks.add(Blocks.IRON_ORE);
        pickaxeBlocks.add(Blocks.COAL_ORE);
        pickaxeBlocks.add(Blocks.DIAMOND_ORE);
        pickaxeBlocks.add(Blocks.GOLD_ORE);
        // ...その他追加可能

        // 斧で破壊可能なブロック
        Set<Block> axeBlocks = new HashSet<>();
        axeBlocks.add(Blocks.OAK_LOG);
        axeBlocks.add(Blocks.OAK_PLANKS);
        axeBlocks.add(Blocks.OAK_FENCE);
        axeBlocks.add(Blocks.OAK_STAIRS);
        axeBlocks.add(Blocks.SPRUCE_LOG);
        axeBlocks.add(Blocks.SPRUCE_PLANKS);
        // ...その他追加可能

        // スコップで破壊可能なブロック
        Set<Block> shovelBlocks = new HashSet<>();
        shovelBlocks.add(Blocks.DIRT);
        shovelBlocks.add(Blocks.GRASS_BLOCK);
        shovelBlocks.add(Blocks.SAND);
        shovelBlocks.add(Blocks.GRAVEL);
        shovelBlocks.add(Blocks.CLAY);
        // ...その他追加可能

        // マッピングを追加
        toolToBlocks.put(Items.WOODEN_PICKAXE, pickaxeBlocks);
        toolToBlocks.put(Items.STONE_PICKAXE, pickaxeBlocks);
        toolToBlocks.put(Items.IRON_PICKAXE, pickaxeBlocks);
        toolToBlocks.put(Items.DIAMOND_PICKAXE, pickaxeBlocks);
        toolToBlocks.put(Items.NETHERITE_PICKAXE, pickaxeBlocks);

        toolToBlocks.put(Items.WOODEN_AXE, axeBlocks);
        toolToBlocks.put(Items.STONE_AXE, axeBlocks);
        toolToBlocks.put(Items.IRON_AXE, axeBlocks);
        toolToBlocks.put(Items.DIAMOND_AXE, axeBlocks);
        toolToBlocks.put(Items.NETHERITE_AXE, axeBlocks);

        toolToBlocks.put(Items.WOODEN_SHOVEL, shovelBlocks);
        toolToBlocks.put(Items.STONE_SHOVEL, shovelBlocks);
        toolToBlocks.put(Items.IRON_SHOVEL, shovelBlocks);
        toolToBlocks.put(Items.DIAMOND_SHOVEL, shovelBlocks);
        toolToBlocks.put(Items.NETHERITE_SHOVEL, shovelBlocks);
    }
}

