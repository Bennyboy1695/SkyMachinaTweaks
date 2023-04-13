package io.github.bennyboy1695.skymachinatweaks.util;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class FluidStackUtils {

    @Nullable
    public static FluidStack jsonDeserializeFluidStack(JsonObject jsonObject) {
        @Nullable
        final Fluid fluid =
                ForgeRegistries.FLUIDS.getValue(
                        new ResourceLocation(GsonHelper.getAsString(jsonObject, "fluid")));
        if (fluid == null) {
            return null;
        }
        FluidStack fluidStack = new FluidStack(fluid, FluidType.BUCKET_VOLUME);
        if (GsonHelper.isValidNode(jsonObject, "tag")) {
            fluidStack.setTag(JsonUtils.readNBT(jsonObject, "tag"));
        }
        return fluidStack;
    }
}
