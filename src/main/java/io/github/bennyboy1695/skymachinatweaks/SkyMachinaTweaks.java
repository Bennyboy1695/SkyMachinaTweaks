package io.github.bennyboy1695.skymachinatweaks;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import io.github.bennyboy1695.skymachinatweaks.config.Config;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.CrucibleRecipe;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.HeatRecipe;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.gen.SkyMachinaRecipe;
import io.github.bennyboy1695.skymachinatweaks.register.*;
import io.github.bennyboy1695.skymachinatweaks.util.MachinaCreativeTab;
import io.github.bennyboy1695.skymachinatweaks.util.RecipeCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Mod(SkyMachinaTweaks.MOD_ID)
public class SkyMachinaTweaks {

    public static final String MOD_ID = "skymachinatweaks";
    private static SkyMachinaTweaks instance;
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);
    private static final CreateRegistrate register = REGISTRATE.creativeModeTab(() -> new MachinaCreativeTab(SkyMachinaTweaks.MOD_ID));
    private static final Logger logger = LogUtils.getLogger();
    private RecipeCache recipeCache;

    public SkyMachinaTweaks() {
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        CreateRegistrate r = REGISTRATE;

        recipeCache = new RecipeCache();

        //Just to initialize for the register
        MachinaItems.values();
        MachinaBlocks.values();
        MachinaFluids.values();
        MachinaTiles.register();

        MachinaRecipeSerializers.init(modEventBus);
        MachinaRecipeTypes.init(modEventBus);
        ModRecipeTypes.register(modEventBus);

        forgeEventBus.addListener(this::serverStart);
        forgeEventBus.addListener(this::gatherData);

        generateLangTweaks();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SkyMachinaTweaksClient.onClient(modEventBus, forgeEventBus));
    }

    private void serverStart(ServerStartedEvent event) {
        if (event.getServer().isDedicatedServer()) {
            loadRecipes(event.getServer().getRecipeManager());
        }
    }

    private void gatherData(GatherDataEvent event) {
        if (event.includeServer()) {
            SkyMachinaRecipe.registerAllProcessingProviders(event.getGenerator());
        }
    }

    private static void loadRecipes(RecipeManager manager) {
        @Nonnull final Collection<Recipe<?>> recipes = manager.getRecipes();
        if (recipes.isEmpty()) {
            return;
        }
        SkyMachinaTweaks.getInstance().recipeCache().setRecipes(
                filterRecipes(
                        recipes, CrucibleRecipe.class, MachinaRecipeTypes.CRUCIBLE_RECIPE_TYPE.get()));
        SkyMachinaTweaks.getInstance().recipeCache().setHeatRecipes(
                filterRecipes(recipes, HeatRecipe.class, MachinaRecipeTypes.HEAT_RECIPE_TYPE.get()));
    }

    private static <R extends Recipe<?>> List<R> filterRecipes(@Nonnull final Collection<Recipe<?>> recipes, @Nonnull final Class<R> recipeClass, @Nonnull final RecipeType<R> recipeType) {
        return recipes.stream()
                .filter(iRecipe -> iRecipe.getType() == recipeType)
                .map(recipeClass::cast)
                .collect(Collectors.toList());
    }

    public static CreateRegistrate register() {
        return register;
    }

    private void generateLangTweaks() {
        REGISTRATE.addRawLang("itemGroup.skymachinatweaks", "Sky Machina Tweaks");
    }

    public static Logger getLogger() {
        return logger;
    }

    public RecipeCache recipeCache() {
        return recipeCache;
    }

    public static SkyMachinaTweaks getInstance() {
        return instance;
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
