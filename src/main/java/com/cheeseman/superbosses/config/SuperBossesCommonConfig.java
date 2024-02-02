package com.cheeseman.superbosses.config;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class SuperBossesCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> SUPER_BOSS_LOOT_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> SUPER_DUPER_BOSS_LOOT_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> MEGA_BOSS_LOOT_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> ULTRA_MEGA_BOSS_LOOT_MULTIPLIER;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> WHITELIST;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> BLACKLIST;

    static {
        BUILDER
                .comment("After changing config either restart your game, or simply restart your world and use the /reload command")
                .push("Super Bosses Config");

        SUPER_BOSS_LOOT_MULTIPLIER = BUILDER.comment("The amount Super Bosses will multiply their drops by")
                .define("Super Loot Multiplier", 4);
        SUPER_DUPER_BOSS_LOOT_MULTIPLIER = BUILDER.comment("The amount Super Duper Bosses will multiply their drops by")
                .define("Super Duper Loot Multiplier", 8);
        MEGA_BOSS_LOOT_MULTIPLIER = BUILDER.comment("The amount MEGA Bosses will multiply their drops by")
                .define("MEGA Loot Multiplier", 22);
        ULTRA_MEGA_BOSS_LOOT_MULTIPLIER = BUILDER.comment("The amount ULTRA MEGA Bosses will multiply their drops by")
                .define("ULTRA MEGA Loot Multiplier", 55);

        WHITELIST = BUILDER.comment("\nWhitelist of mobs that will be able to spawn as Super Bosses. (example: \"minecraft:pig\") \nBy default any mob classified as a Monster will be included. If you add any invalid entities Super Bosses will no longer spawn!")
                .defineList("Mob Whitelist", ImmutableList.of(), o -> o instanceof String);
        BLACKLIST = BUILDER.comment("Blacklist of mobs that will not spawn as Super Bosses (example: \"minecraft:wither\")")
                .defineList("Mob Blacklist", ImmutableList.of(), o -> o instanceof String);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}