package io.github.bennyboy1695.skymachinatweaks.util;

import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.CrucibleRecipe;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.HeatRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import java.util.*;

public class RecipeCache {

    @Nonnull
    private final List<CrucibleRecipe> crucibleRecipes = new ArrayList<>();
    @Nonnull private final Map<Item, CrucibleRecipe> crucibleRecipeCache = new HashMap<>();

    public void setRecipes(@Nonnull final List<CrucibleRecipe> recipes) {
        SkyMachinaTweaks.getLogger().debug("Crucible Registry recipes: " + recipes.size());
        crucibleRecipes.addAll(recipes);
    }

    @Nonnull
    public Optional<CrucibleRecipe> findRecipeByItemStack(@Nonnull final ItemStack itemStack) {
        return Optional.ofNullable(
                crucibleRecipeCache.computeIfAbsent(
                        itemStack.getItem(),
                        k ->
                                crucibleRecipes.stream()
                                        .filter(recipe -> recipe.input().test(itemStack))
                                        .findFirst()
                                        .orElse(null)));
    }

    @Nonnull
    public Optional<CrucibleRecipe> findRecipeByItem(@Nonnull final Item item) {
        return Optional.ofNullable(
                crucibleRecipeCache.computeIfAbsent(
                        item,
                        k -> {
                            final ItemStack itemStack = new ItemStack(item);
                            return crucibleRecipes.stream()
                                    .filter(recipe -> recipe.input().test(itemStack))
                                    .findFirst()
                                    .orElse(null);
                        }));
    }

    public boolean isMeltable(@Nonnull final ItemLike item) {
        return isMeltableByItem(item.asItem());
    }

    public boolean isMeltableByItemStack(@Nonnull final ItemStack itemStack) {
        final Optional<CrucibleRecipe> recipe = findRecipeByItemStack(itemStack);
        return recipe.isPresent();
    }

    public boolean isMeltableByItem(@Nonnull final Item item) {
        final Optional<CrucibleRecipe> recipe = findRecipeByItem(item);
        return recipe.isPresent();
    }

    public void clearCrucibleRecipes() {
        crucibleRecipes.clear();
        crucibleRecipeCache.clear();
    }

    @Nonnull private final List<HeatRecipe> heatRecipes = new ArrayList<>();

    public void clearHeatRecipes() {
        heatRecipes.clear();
    }

    public int getHeatAmount(@Nonnull final BlockState entry) {
        return heatRecipes.stream()
                .filter(recipe -> recipe.isMatch(entry))
                .findFirst()
                .map(HeatRecipe::getAmount)
                .orElse(0);
    }

    @Nonnull
    public List<HeatRecipe> getHeatRecipeList() {
        return heatRecipes;
    }

    public void setHeatRecipes(@Nonnull final List<HeatRecipe> recipes) {
        SkyMachinaTweaks.getLogger().debug("Heat Registry recipes: " + recipes.size());
        heatRecipes.addAll(recipes);
    }

}
