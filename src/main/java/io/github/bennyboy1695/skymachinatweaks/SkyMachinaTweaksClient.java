package io.github.bennyboy1695.skymachinatweaks;

import io.github.bennyboy1695.skymachinatweaks.data.recipe.CrucibleRecipe;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.HeatRecipe;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaRecipeTypes;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SkyMachinaTweaksClient {

    public static void onClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(SkyMachinaTweaksClient::clientInit);
        forgeEventBus.addListener(SkyMachinaTweaksClient::clearRegistries);
        forgeEventBus.addListener(SkyMachinaTweaksClient::loadClientRecipes);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
    }

    private static void clearRegistries(ClientPlayerNetworkEvent.LoggingOut event) {
        SkyMachinaTweaks.getInstance().recipeCache().clearHeatRecipes();
        SkyMachinaTweaks.getInstance().recipeCache().clearCrucibleRecipes();
    }

    public static void loadClientRecipes(RecipesUpdatedEvent event) {
        SkyMachinaTweaks.getInstance().recipeCache().clearHeatRecipes();
        SkyMachinaTweaks.getInstance().recipeCache().clearCrucibleRecipes();
        loadRecipes(event.getRecipeManager());
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
}
