package com.cheeseman.superbosses.util;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class LootJSReport {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void initReports () {
        LOGGER.info("THINGY 2 RAN");
        File fda = new File(getGameDirectory(),"/kubejs");
        if (!fda.exists())
            fda.mkdir();
        File fdb = new File(getGameDirectory(),"/kubejs/server_scripts");
        if (!fdb.exists())
            fdb.mkdir();
        File fdc = new File(getGameDirectory(),"/kubejs/server_scripts/superbosses");
        if (!fdc.exists())
            fdc.mkdir();
        File fb = new File(getGameDirectory(),"/kubejs/server_scripts/superbosses/superbosses_loot.js");
        if (fb.exists())
            fb.delete();
    }

    @SuppressWarnings("deprecation")
    public static void doReport() {

        boolean isFirst = true;
        PrintStream p = null;
        try {
            p = new PrintStream(new FileOutputStream(getGameDirectory().getPath() + "/kubejs/server_scripts/superbosses/superbosses_loot.js", true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (p == null) {
            p = System.out;
        }

        p.print("priority: 0\n" +
                "\n" +
                "onEvent(\"lootjs\", (event) => {\n" +
                "    event\n" +
                "        .addLootTypeModifier([LootType.ENTITY])\n" +
                "        .entityPredicate(entity => {\n" +
                "        return entity.asKJS().fullNBT.ForgeData.SuperBoss == 1\n" +
                "        })\n" +
                "        .modifyLoot(Ingredient.getAll(), (itemStack) => {\n" +
                "            itemStack.setCount(itemStack.getCount() * 4);\n" +
                "            return itemStack;\n" +
                "        })\n" +
                "\n" +
                "});\n" +
                "\n" +
                "onEvent(\"lootjs\", (event) => {\n" +
                "    event\n" +
                "        .addLootTypeModifier([LootType.ENTITY])\n" +
                "        .entityPredicate(entity => {\n" +
                "        return entity.asKJS().fullNBT.ForgeData.SuperDuperBoss == 1\n" +
                "        })\n" +
                "        .modifyLoot(Ingredient.getAll(), (itemStack) => {\n" +
                "            itemStack.setCount(itemStack.getCount() * 8);\n" +
                "            return itemStack;\n" +
                "        });\n" +
                "\n" +
                "});\n" +
                "\n" +
                "onEvent(\"lootjs\", (event) => {\n" +
                "    event\n" +
                "        .addLootTypeModifier([LootType.ENTITY])\n" +
                "        .entityPredicate(entity => {\n" +
                "        return entity.asKJS().fullNBT.ForgeData.MegaBoss == 1\n" +
                "        })\n" +
                "        .modifyLoot(Ingredient.getAll(), (itemStack) => {\n" +
                "            itemStack.setCount(itemStack.getCount() * 22);\n" +
                "            return itemStack;\n" +
                "        });\n" +
                "\n" +
                "});\n" +
                "\n" +
                "onEvent(\"lootjs\", (event) => {\n" +
                "    event\n" +
                "        .addLootTypeModifier([LootType.ENTITY])\n" +
                "        .entityPredicate(entity => {\n" +
                "        return entity.asKJS().fullNBT.ForgeData.UltraMegaBoss == 1\n" +
                "        })\n" +
                "        .modifyLoot(Ingredient.getAll(), (itemStack) => {\n" +
                "            itemStack.setCount(itemStack.getCount() * 55);\n" +
                "            return itemStack;\n" +
                "        });\n" +
                "\n" +
                "});");

        if (p != System.out) {
            p.close();
        }

    }


    public static File getGameDirectory() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            return Minecraft.getInstance().gameDirectory;
        } else {
            return new File("");
        }
    }

}