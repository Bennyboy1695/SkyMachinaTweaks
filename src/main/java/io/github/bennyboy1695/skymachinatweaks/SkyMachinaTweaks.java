package io.github.bennyboy1695.skymachinatweaks;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import io.github.bennyboy1695.skymachinatweaks.compat.create.SifterArmInteraction;
import io.github.bennyboy1695.skymachinatweaks.config.Config;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaBlocks;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaFluids;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaItems;
import io.github.bennyboy1695.skymachinatweaks.util.MachinaCreativeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.concurrent.ThreadLocalRandom;


@Mod(SkyMachinaTweaks.MOD_ID)
public class SkyMachinaTweaks {

    public static final String MOD_ID = "skymachinatweaks";
    private static SkyMachinaTweaks instance;
    private static final NonNullSupplier<CreateRegistrate> REGISTRATE = CreateRegistrate.lazy(MOD_ID);
    private static final CreateRegistrate register = REGISTRATE.get().creativeModeTab(() -> new MachinaCreativeTab(SkyMachinaTweaks.MOD_ID));
    private static final Logger logger = LogUtils.getLogger();

    public SkyMachinaTweaks() {
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        CreateRegistrate r = REGISTRATE.get();

        //Just to initialize for the register
        MachinaItems.values();
        MachinaBlocks.values();
        MachinaFluids.values();

        SifterArmInteraction.register();

        generateLangTweaks();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SkyMachinaTweaksClient.onClient(modEventBus, forgeEventBus));
    }

    public static CreateRegistrate register() {
        return register;
    }

    private void generateLangTweaks() {
        REGISTRATE.get().addRawLang("itemGroup.skymachinatweaks", "Sky Machina Tweaks");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static SkyMachinaTweaks getInstance() {
        return instance;
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
