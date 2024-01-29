package com.cheeseman.superbosses.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import com.cheeseman.superbosses.config.SuperBossesCommonConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

public class MobEntityReport {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void initReports () {
        LOGGER.info("THINGY RAN");
        File fda = new File("kubejs");
        if (!fda.exists())
            fda.mkdir();
        File fdb = new File("kubejs/data");
        if (!fdb.exists())
            fdb.mkdir();
        File fdc = new File("kubejs/data/superbosses");
        if (!fdc.exists())
            fdc.mkdir();
        File fdd = new File("kubejs/data/superbosses/tags");
        if (!fdd.exists())
            fdd.mkdir();
        File fde = new File("kubejs/data/superbosses/tags/entity_types");
        if (!fde.exists())
            fde.mkdir();
        File fb = new File("kubejs/data/superbosses/tags/entity_types/monsters.json");
        if (fb.exists())
            fb.delete();
    }

    @SuppressWarnings("deprecation")
    public static void doReport() {

        boolean isFirst = true;
        PrintStream p = null;
        try {
            p = new PrintStream(new FileOutputStream("kubejs/data/superbosses/tags/entity_types/monsters.json", true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (p == null) {
            p = System.out;
        }

        p.print("{\n" +
                "    \"replace\": false,\n" +
                "    \"values\": [\n");

        for (EntityType<?> a : BuiltInRegistries.ENTITY_TYPE) {
            String entityString = EntityType.getKey(a).toString();
            if (isValidClassification(a) && isMobNotBlacklisted(entityString)) {
                p.println(firstCheck(isFirst) + '\"' + entityString + '\"');
                isFirst = false;
            }
        }

        for (String mobString : getMobWhitelist()) {
            p.println(firstCheck(isFirst) + '\"' + mobString + '\"');
            isFirst = false;
        }

        p.print("    ]\n" +
                "  }\n");

        if (p != System.out) {
            p.close();
        }

    }

    private static boolean isValidClassification(EntityType<?> a) {
        if (a.getCategory() == MobCategory.MONSTER)
            return true;
        return false;
    }

    public static File getGameDirectory() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            return Minecraft.getInstance().gameDirectory;
        } else {
            return new File("");
        }
    }

    public static String firstCheck(boolean isFirst) {
        if (!isFirst) {
            return ",";
        }
        else {
            return "";
        }
    }

    public static List<? extends String> getMobWhitelist() {
        return SuperBossesCommonConfig.WHITELIST.get();
    }
    public static List<? extends String> getMobBlacklist() {
        return SuperBossesCommonConfig.BLACKLIST.get();
    }
    public static boolean isMobNotBlacklisted(String entityString) {
        if (getMobBlacklist().contains(entityString)) {
            return false;
        }
        return true;
    }

}