package io.github.bennyboy1695.skymachinatweaks.data.recipe.gen;

import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import io.github.bennyboy1695.skymachinatweaks.register.ModRecipeTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class BeaterRecipeGen extends SkyMachinaRecipe {

    CreateRecipeProvider.GeneratedRecipe TEST_RECIPE = create("test_recipe", builder -> builder
            .require(Blocks.DIRT)
            .output(.8f, Items.COBBLESTONE)
            .output(.8f, Items.GRANITE)
            .output(.8f, Items.DIORITE)
            .output(.8f, Items.ANDESITE)
    );


    public BeaterRecipeGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return ModRecipeTypes.BEATER;
    }
}
