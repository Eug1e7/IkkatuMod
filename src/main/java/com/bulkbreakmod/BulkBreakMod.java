package com.bulkbreakmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
        Config.loadConfig();

        // MinecraftForge イベントバスへ登録
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // サーバー・クライアント共通の初期化
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }
}