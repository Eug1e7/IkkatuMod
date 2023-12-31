// C:\Mod\ikkatuMod\src\main\java\com\bulkbreakmod\BulkBreakMod.java

package com.bulkbreakmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

@Mod(BulkBreakMod.MODID)
public class BulkBreakMod {

    // この行を追加して Logger インスタンスを作成
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "bulkbreakmod";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> MY_CUSTOM_PICKAXE = ITEMS.register("my_custom_pickaxe", () ->
            new MyCustomPickaxe(ItemTier.DIAMOND, 2.0F, -2.8F, new Item.Properties().tab(ItemGroup.TAB_TOOLS))
    );

    public static final RegistryObject<Item> MY_CUSTOM_AXE = ITEMS.register("my_custom_axe", () ->
            new MyCustomAxe(ItemTier.DIAMOND, 5.0F, -3.0F, new Item.Properties().tab(ItemGroup.TAB_TOOLS))
    );

    public static final RegistryObject<Item> MY_CUSTOM_SHOVEL = ITEMS.register("my_custom_shovel", () ->
            new MyCustomShovel(ItemTier.DIAMOND, 1.5F, -3.0F, new Item.Properties().tab(ItemGroup.TAB_TOOLS))
    );

    public void checkIfItemIsRegistered() {
        // こちらの System.out.println() を LOGGER に置き換えます
        String itemNameToCheck = "my_custom_pickaxe";
        if (BulkBreakMod.MY_CUSTOM_PICKAXE.isPresent()) {
            Item item = BulkBreakMod.MY_CUSTOM_PICKAXE.get();
            // ログ出力の方法を変更
            LOGGER.info(itemNameToCheck + " は登録されています。");
        } else {
            // ログ出力の方法を変更
            LOGGER.warn(itemNameToCheck + " は登録されていません。");
        }
    }


    public BulkBreakMod() {
        // Mod イベントバスを取得
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // DeferredRegisterをイベントバスに登録
        ITEMS.register(modEventBus);

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
        checkIfItemIsRegistered();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        ItemStack heldItemStack = player.getItemInHand(Hand.MAIN_HAND);
        Item heldItem = heldItemStack.getItem();
        World world = (World) event.getWorld();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();

        // しゃがんでいるときは一括破壊しない
        if (player.isCrouching()) {
            return;
        }

        int limit = Config.MAX_BREAK_LIMIT.get();  // コンフィグから最大破壊数を取得
        int count = 0;

        // ツールと破壊可能なブロックのマッピングから、破壊可能なブロックのセットを取得
        // マッピングにない場合は新たなHashSetを作成（一度取得したら再利用）
        Set<Block> breakableBlocks = ToolBlockMapping.toolToBlocks.getOrDefault(heldItem, new HashSet<>());

        // 破壊可能なブロックであれば一括破壊処理を実施
        if (breakableBlocks.contains(state.getBlock())) {
            // 一括破壊処理（簡易版）
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        // 最大破壊数に達したら処理を終了
                        if (count >= limit) {
                            return;
                        }
                        BlockPos newPos = new BlockPos(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                        BlockState newState = world.getBlockState(newPos);

                        // 同じ種類のブロックであれば破壊
                        if (newState.equals(state)) {
                            Block.dropResources(newState, world, newPos);  // ドロップアイテムを出現させる
                            world.removeBlock(newPos, false);  // ブロックを破壊
                            count++;  // 破壊数をインクリメント
                        }
                    }
                }
            }
        }
    }
}
