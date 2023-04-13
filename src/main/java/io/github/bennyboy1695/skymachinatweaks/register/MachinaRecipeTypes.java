package io.github.bennyboy1695.skymachinatweaks.register;

import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.CrucibleRecipe;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.HeatRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MachinaRecipeTypes {

    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, SkyMachinaTweaks.MOD_ID);


    public static final RegistryObject<RecipeType<HeatRecipe>> HEAT_RECIPE_TYPE =
            RECIPE_TYPES.register("heat", () -> new RecipeType<HeatRecipe>() {});
    public static final RegistryObject<RecipeType<CrucibleRecipe>> CRUCIBLE_RECIPE_TYPE =
            RECIPE_TYPES.register("crucible", () -> new RecipeType<CrucibleRecipe>() {});


    public static void init(IEventBus modEventBus) {
        RECIPE_TYPES.register(modEventBus);
    }
}
