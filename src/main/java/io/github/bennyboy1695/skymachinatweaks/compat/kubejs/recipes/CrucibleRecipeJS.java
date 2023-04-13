package io.github.bennyboy1695.skymachinatweaks.compat.kubejs.recipes;

import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class CrucibleRecipeJS extends RecipeJS {

    private Ingredient input;
    private int amount;
    private FluidStackJS fluidResult;

    @Override
    public void create(RecipeArguments args) {
        this.input = this.parseItemInput(args.get(0));
        this.amount = args.getInt(2, 0);
        this.fluidResult = FluidStackJS.of(args.get(3));
    }

    @Override
    public void deserialize() {
        this.input = this.parseItemInput(this.json.get("input"));
        this.amount = this.json.get("amount").getAsInt();
        this.fluidResult = FluidStackJS.fromJson(this.json.get("fluidResult"));
    }

    @Override
    public void serialize() {
        if (this.serializeInputs) {
            this.json.add("input", this.input.toJson());
        }
        this.json.addProperty("amount", this.amount);
        this.json.add("fluidResult", this.fluidResult.toJson());
    }

    @Override
    public boolean hasInput(IngredientMatch ingredientMatch) {
        return ingredientMatch.contains(this.input);
    }

    @Override
    public boolean replaceInput(
            IngredientMatch ingredientMatch,
            Ingredient ingredient,
            ItemInputTransformer itemInputTransformer) {
        if (ingredientMatch.contains(this.input)) {
            this.input = itemInputTransformer.transform(this, ingredientMatch, this.input, ingredient);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasOutput(IngredientMatch ingredientMatch) {
        return false;
    }

    @Override
    public boolean replaceOutput(
            IngredientMatch ingredientMatch,
            ItemStack itemStack,
            ItemOutputTransformer itemOutputTransformer) {
        return false;
    }
}
