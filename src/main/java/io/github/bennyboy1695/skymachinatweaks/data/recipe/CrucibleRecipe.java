package io.github.bennyboy1695.skymachinatweaks.data.recipe;

import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaRecipeSerializers;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class CrucibleRecipe implements Recipe<Container> {

    private int amount;
    @Nonnull private FluidStack resultFluid;
    private Ingredient input;

    public CrucibleRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input, final int amount, @Nonnull final FluidStack fluid) {
        this.amount = amount;
        this.input = input;
        this.resultFluid = fluid;
    }

    public Ingredient input() {
        return input;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean matches(Container p_44002_, Level p_44003_) {
        return false;
    }

    @Override
    public ItemStack assemble(Container p_44001_) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return false;
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return SkyMachinaTweaks.asResource("crucible");
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MachinaRecipeSerializers.CRUCIBLE_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MachinaRecipeTypes.CRUCIBLE_RECIPE_TYPE.get();
    }

    @Nonnull
    public FluidStack getResultFluid() {
        return resultFluid;
    }

    public void setResultFluid(@Nonnull final FluidStack resultFluid) {
        this.resultFluid = resultFluid;
    }

}
