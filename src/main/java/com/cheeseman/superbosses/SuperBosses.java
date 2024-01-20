package com.cheeseman.superbosses;

import com.cheeseman.superbosses.util.LootJSReport;
import com.cheeseman.superbosses.util.MobEntityReport;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SuperBosses.MOD_ID)
public class SuperBosses {
    public static final String MOD_ID = "superbosses";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SuperBosses() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void preInit (final FMLCommonSetupEvent event) {
        MobEntityReport.initReports();
        MobEntityReport.doReport();
        LootJSReport.initReports();
        LootJSReport.doReport();
    }

    @Mod.EventBusSubscriber()
    public static class ForgeEvents {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onServerAboutToStart(ServerAboutToStartEvent event) {
            MobEntityReport.initReports();
            MobEntityReport.doReport();
            LootJSReport.initReports();
            LootJSReport.doReport();
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
    }
}