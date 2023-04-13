package io.github.bennyboy1695.skymachinatweaks.data.serializer;

import com.google.gson.JsonObject;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.CrucibleRecipe;
import io.github.bennyboy1695.skymachinatweaks.util.FluidStackUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleRecipeSerializer implements RecipeSerializer<CrucibleRecipe> {

    @Override
    @Nonnull
    public CrucibleRecipe fromNetwork(@Nonnull final ResourceLocation recipeId, @Nonnull final FriendlyByteBuf buffer) {
        @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
        final int amount = buffer.readInt();
        @Nonnull final FluidStack fluid = FluidStack.readFromPacket(buffer);
        return new CrucibleRecipe(recipeId, input, amount, fluid);
    }

    @Override
    public void toNetwork(@Nonnull final FriendlyByteBuf buffer, @Nonnull final CrucibleRecipe recipe) {
        recipe.input().toNetwork(buffer);
        buffer.writeInt(recipe.getAmount());
        recipe.getResultFluid().writeToPacket(buffer);
    }

    @Override
    @Nonnull
    public CrucibleRecipe fromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
        @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
        final int amount = json.get("amount").getAsInt();
        @Nullable final FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluidResult").getAsJsonObject());
        return new CrucibleRecipe(recipeId, input, amount, fluid);
    }
}
