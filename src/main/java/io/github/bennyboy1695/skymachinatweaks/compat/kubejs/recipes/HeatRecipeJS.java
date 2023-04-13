package io.github.bennyboy1695.skymachinatweaks.compat.kubejs.recipes;

import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.util.MapJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class HeatRecipeJS extends RecipeJS {

    private ItemStack block;
    private int amount;
    private Map<?, ?> state;

    @Override
    public void create(RecipeArguments args) {
        this.block = ItemStackJS.of(args.get(0));
        this.amount = args.getInt(1, 0);
        this.state = MapJS.of(args.get(2));
    }

    @Override
    public void deserialize() {
        this.block = ItemStackJS.of(this.json.get("block"));
        this.amount = this.json.get("amount").getAsInt();
        this.state = MapJS.of(this.json.get("state"));
    }

    @Override
    public void serialize() {
        this.json.addProperty("block", ForgeRegistries.ITEMS.getKey(this.block.getItem()).toString());
        this.json.addProperty("amount", this.amount);
        this.json.add("state", MapJS.json(this.state));
    }

    @Override
    public boolean hasInput(IngredientMatch ingredientMatch) {
        return false;
    }

    @Override
    public boolean replaceInput(IngredientMatch ingredientMatch, Ingredient ingredient, ItemInputTransformer itemInputTransformer) {
        return false;
    }

    @Override
    public boolean hasOutput(IngredientMatch ingredientMatch) {
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch ingredientMatch, ItemStack itemStack, ItemOutputTransformer itemOutputTransformer) {
        return false;
    }
}
