package io.github.bennyboy1695.skymachinatweaks.compat.kubejs.recipes;

import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class BeaterRecipeJS extends RecipeJS {

    Ingredient input;
    List<ItemStack> output = new ArrayList<>();

    @Override
    public void create(RecipeArguments args) {
        this.input = this.parseItemInput(args.get(0));
        for (var result : ListJS.orSelf(args.get(0))) {
            output.add(parseItemOutput(result));
        }
    }

    @Override
    public void deserialize() {
        this.input = parseItemInput(json.get("ingredients").getAsJsonArray().get(0));

        for (var result : json.get("results").getAsJsonArray()) {
            var resultJson = result.getAsJsonObject();
            output.add(parseItemOutput(result));
        }
    }

    @Override
    public void serialize() {
        if (serializeInputs) {
            var jsonIngredients = new JsonArray();
            jsonIngredients.add(input.toJson());

            json.add("ingredients", jsonIngredients);
        }

        if (serializeOutputs) {
            var jsonOutputs = new JsonArray();

            for (var item : output) {
                jsonOutputs.add(itemToJson(item));
            }

            json.add("results", jsonOutputs);
        }
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(input);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        boolean changed = false;

        if (hasInput(match)) {
            if (match.contains(input)) {
                this.input = transformer.transform(this, match, input, with);
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        for (var out : output) {
            if (match.contains(out)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        boolean changed = false;

        if (hasOutput(match)) {
            for (int i = 0; i < output.size(); i++) {
                var outputItem = output.get(i);

                if (match.contains(outputItem)) {
                    output.set(i, transformer.transform(this, match, outputItem, with));
                    changed = true;
                }
            }
        }

        return changed;
    }
}
