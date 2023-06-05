package io.github.bennyboy1695.skymachinatweaks.register;

import com.tterrag.registrate.util.entry.FluidEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import net.minecraft.resources.ResourceLocation;

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
