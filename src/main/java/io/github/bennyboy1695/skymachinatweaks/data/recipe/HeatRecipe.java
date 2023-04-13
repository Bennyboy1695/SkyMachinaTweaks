package io.github.bennyboy1695.skymachinatweaks.data.recipe;

import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaRecipeSerializers;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaRecipeTypes;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HeatRecipe implements Recipe<Container> {

    private int amount;
    @Nullable private Block input;
    @Nullable private StatePropertiesPredicate properties;

    public HeatRecipe(@Nonnull final ResourceLocation id, @Nullable final Block input, final int amount) {
        this.input = input;
        this.amount = amount;
        this.properties = null;
    }

    public HeatRecipe(@Nonnull final ResourceLocation id, @Nonnull final Block input, final int amount, @Nonnull final StatePropertiesPredicate properties) {
        this.input = input;
        this.amount = amount;
        this.properties = properties;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    @Nullable
    public Block getInput() {
        return input;
    }

    public void setInput(@Nonnull final Block input) {
        this.input = input;
    }

    @Nullable
    public StatePropertiesPredicate getProperties() {
        return this.properties;
    }

    public void setProperties(@Nonnull final StatePropertiesPredicate properties) {
        this.properties = properties;
    }

    public boolean isMatch(@Nonnull final BlockState state) {
        if (input == null) {
            return false;
        }
        @Nullable
        final ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(state.getBlock());
        if (resourceLocation == null) {
            return false;
        }
        return resourceLocation.equals(ForgeRegistries.BLOCKS.getKey(input))
                && (properties == null || properties.matches(state));
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
    public @NotNull ResourceLocation getId() {
        return SkyMachinaTweaks.asResource("heat");
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MachinaRecipeSerializers.HEAT_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return MachinaRecipeTypes.HEAT_RECIPE_TYPE.get();
    }
}
