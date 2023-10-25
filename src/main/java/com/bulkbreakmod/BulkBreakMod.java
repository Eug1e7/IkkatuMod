// C:\Mod\ikkatuMod\src\main\java\com\bulkbreakmod\BulkBreakMod.java

package com.bulkbreakmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BulkBreakMod.MODID)
public class BulkBreakMod {
    public static final String MODID = "bulkbreakmod";

    public BulkBreakMod() {
        // Mod イベントバスを取得
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //各種初期化イベント
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        // コンフィグをロード
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG);

        // MinecraftForge イベントバスへ登録
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // サーバー・クライアント共通の初期化
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        World world = (World) event.getWorld();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();

        if (player.isCrouching()) { // しゃがんでいるときは一括破壊しない
            return;
        }

        int limit = Config.MAX_BREAK_LIMIT.get();  // コンフィグから最大破壊数を取得
        int count = 0;

        // 一括破壊処理（簡易版）
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (count >= limit) {
                        return;
                    }
                    BlockPos newPos = new BlockPos(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                    BlockState newState = world.getBlockState(newPos);
                    if (newState.equals(state)) {
                        Block.dropResources(newState, world, newPos);  // ドロップアイテムを出現させる
                        world.removeBlock(newPos, false);  // ブロックを破壊
                        count++;
                    }
                }
            }
        }
    }
}