package com.cheeseman.superbosses.config;

import net.minecraftforge.common.ForgeConfigSpec;

import javax.xml.stream.events.Comment;

public class SuperBossesCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> SUPER_BOSS_LOOT_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> SUPER_DUPER_BOSS_LOOT_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> MEGA_BOSS_LOOT_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> ULTRA_MEGA_BOSS_LOOT_MULTIPLIER;

    static {
        BUILDER
                .comment("After changing config either restart your game, or simply restart your world and use the /reload command")
                .push("Super Bosses Config");

        SUPER_BOSS_LOOT_MULTIPLIER = BUILDER.comment("The amount Super Bosses will multiply their drops by")
                .define("Super Multiplier", 4);
        SUPER_DUPER_BOSS_LOOT_MULTIPLIER = BUILDER.comment("The amount Super Duper Bosses will multiply their drops by")
                .define("Super Duper Multiplier", 8);
        MEGA_BOSS_LOOT_MULTIPLIER = BUILDER.comment("The amount MEGA Bosses will multiply their drops by")
                .define("MEGA Multiplier", 22);
        ULTRA_MEGA_BOSS_LOOT_MULTIPLIER = BUILDER.comment("The amount ULTRA MEGA Bosses will multiply their drops by")
                .define("ULTRA MEGA Multiplier", 55);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}