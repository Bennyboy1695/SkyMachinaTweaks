package io.github.bennyboy1695.skymachinatweaks.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {

    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static final Common COMMON = new Common();

    public static final class Common {

        public final ForgeConfigSpec.IntValue maxVineLength;

        public Common() {
            maxVineLength = COMMON_BUILDER.comment("The max length a vine can grow down").defineInRange("Tweaks.MaxVineLength", 16, 0, 384);
        }
    }

    public static final ForgeConfigSpec COMMON_CONFIG = COMMON_BUILDER.build();
}