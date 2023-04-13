package io.github.bennyboy1695.skymachinatweaks.register;

import com.simibubi.create.content.contraptions.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.FluidEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.util.ColorUtils;
import io.github.bennyboy1695.skymachinatweaks.util.ItemColorImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public enum MachinaFluids {
    ;

    private final FluidEntry<?> fluidEntry;

    MachinaFluids(FluidEntry<?> fluidEntry) {
        this.fluidEntry = fluidEntry;
    }

    public FluidEntry<?> getFluidEntry() {
        return fluidEntry;
    }

    public static ResourceLocation still() {
        return SkyMachinaTweaks.asResource("block/thick_fluid_still");
    }

    public static ResourceLocation flowing() {
        return SkyMachinaTweaks.asResource("block/thick_fluid_flow");
    }
}
