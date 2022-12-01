package io.github.bennyboy1695.skymachinatweaks.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {

    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static final Common COMMON = new Common();

    public static final class Common {

        public final ForgeConfigSpec.IntValue stringMeshDurability;
        public final ForgeConfigSpec.BooleanValue stringMeshTakeDurability;
        public final ForgeConfigSpec.IntValue andesiteMeshDurability;
        public final ForgeConfigSpec.BooleanValue andesiteMeshTakeDurability;
        public final ForgeConfigSpec.IntValue zincMeshDurability;
        public final ForgeConfigSpec.BooleanValue zincMeshTakeDurability;
        public final ForgeConfigSpec.IntValue brassMeshDurability;
        public final ForgeConfigSpec.BooleanValue brassMeshTakeDurability;
        public final ForgeConfigSpec.IntValue bloodMeshDurability;
        public final ForgeConfigSpec.BooleanValue bloodMeshTakeDurability;
        public final ForgeConfigSpec.IntValue manaMeshDurability;
        public final ForgeConfigSpec.BooleanValue manaMeshTakeDurability;
        public final ForgeConfigSpec.IntValue steelMeshDurability;
        public final ForgeConfigSpec.BooleanValue steelMeshTakeDurability;
        public final ForgeConfigSpec.IntValue leadMeshDurability;
        public final ForgeConfigSpec.BooleanValue leadMeshTakeDurability;
        public final ForgeConfigSpec.IntValue omniMeshDurability;
        public final ForgeConfigSpec.BooleanValue omniMeshTakeDurability;
        public final ForgeConfigSpec.BooleanValue shouldMeshesTakeDurabilityInSifter;

        public final ForgeConfigSpec.DoubleValue durabiltyTakeChance;

        public final ForgeConfigSpec.IntValue maxVineLength;

        public Common() {
            //Mesh Stuff
            stringMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter string mesh should be.").defineInRange("Override.CreateSifter.StringMeshDurability", 64, 0, Integer.MAX_VALUE);
            stringMeshTakeDurability = COMMON_BUILDER.define("Override.CreateSifter.StringMeshTakesDurability", true);
            andesiteMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter andesite mesh should be.").defineInRange("Override.CreateSifter.AndesiteMeshDurability", 128, 0, Integer.MAX_VALUE);
            andesiteMeshTakeDurability = COMMON_BUILDER.define("Override.CreateSifter.AndesiteMeshTakesDurability", true);
            zincMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter zinc mesh should be.").defineInRange("Override.CreateSifter.ZincMeshDurability", 256, 0, Integer.MAX_VALUE);
            zincMeshTakeDurability = COMMON_BUILDER.define("Override.CreateSifter.ZincMeshTakesDurability", true);
            brassMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter brass mesh should be.").defineInRange("Override.CreateSifter.BrassMeshDurability", 512, 0, Integer.MAX_VALUE);
            brassMeshTakeDurability = COMMON_BUILDER.define("Override.CreateSifter.BrassMeshTakesDurability", true);
            bloodMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter blood mesh should be.").defineInRange("CreateSifterAddons.BloodMeshDurability", 512, 0, Integer.MAX_VALUE);
            bloodMeshTakeDurability = COMMON_BUILDER.define("CreateSifterAddons.BloodMeshTakesDurability", true);
            manaMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter mana mesh should be.").defineInRange("CreateSifterAddons.ManaMeshDurability", 512, 0, Integer.MAX_VALUE);
            manaMeshTakeDurability = COMMON_BUILDER.define("CreateSifterAddons.ManaMeshTakesDurability", true);
            steelMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter steel mesh should be.").defineInRange("CreateSifterAddons.SteelMeshDurability", 512, 0, Integer.MAX_VALUE);
            steelMeshTakeDurability = COMMON_BUILDER.define("CreateSifterAddons.SteelMeshTakesDurability", true);
            leadMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter lead mesh should be.").defineInRange("CreateSifterAddons.LeadMeshDurability", 512, 0, Integer.MAX_VALUE);
            leadMeshTakeDurability = COMMON_BUILDER.define("CreateSifterAddons.LeadMeshTakesDurability", true);
            omniMeshDurability = COMMON_BUILDER.comment("What the max durability of the CreateSifter omni mesh should be.").defineInRange("CreateSifterAddons.OmniMeshDurability", 512, 0, Integer.MAX_VALUE);
            omniMeshTakeDurability = COMMON_BUILDER.define("CreateSifterAddons.OmniMeshTakesDurability", true);
            shouldMeshesTakeDurabilityInSifter = COMMON_BUILDER.comment("Toggles whether meshes should also take durability damage in the sifter as it does in a hand").define("Override.CreateSifter.ShouldMeshesTakeDurabilityInSifter", true);
            durabiltyTakeChance = COMMON_BUILDER.comment("The chance to take durability on meshes in the sifter").defineInRange("CreateSifterAddons.DurabilityTakeChance", 0.25, 0.0, 1.0);

            maxVineLength = COMMON_BUILDER.comment("The max length a vine can grow down").defineInRange("Tweaks.MaxVineLength", 16, 0, 384);
        }
    }

    public static final ForgeConfigSpec COMMON_CONFIG = COMMON_BUILDER.build();
}