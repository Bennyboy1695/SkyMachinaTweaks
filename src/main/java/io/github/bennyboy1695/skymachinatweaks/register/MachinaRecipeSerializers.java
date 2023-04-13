package io.github.bennyboy1695.skymachinatweaks.register;

import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.CrucibleRecipe;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.HeatRecipe;
import io.github.bennyboy1695.skymachinatweaks.data.serializer.CrucibleRecipeSerializer;
import io.github.bennyboy1695.skymachinatweaks.data.serializer.HeatRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MachinaRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(
                    ForgeRegistries.RECIPE_SERIALIZERS, SkyMachinaTweaks.MOD_ID);


    public static final RegistryObject<RecipeSerializer<HeatRecipe>> HEAT_RECIPE_SERIALIZER =
            RECIPE_SERIALIZERS.register("heat", HeatRecipeSerializer::new);

    public static final RegistryObject<RecipeSerializer<CrucibleRecipe>> CRUCIBLE_RECIPE_SERIALIZER =
            RECIPE_SERIALIZERS.register("crucible", CrucibleRecipeSerializer::new);


    public static void init(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
