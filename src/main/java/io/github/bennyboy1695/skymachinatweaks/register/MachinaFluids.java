package io.github.bennyboy1695.skymachinatweaks.register;

import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.FluidEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.util.ColorUtils;
import io.github.bennyboy1695.skymachinatweaks.util.ItemColorImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public enum MachinaFluids {

    OVERWORLD(SkyMachinaTweaks.register().fluid("liquid_overworld", still(), flowing())
            .attributes(a -> a.color(ColorUtils.intColor(41, 201, 10)))
            .lang("Overworld")
            .source(ForgeFlowingFluid.Source::new)
            .bucket()
                .lang("Liquid Overworld Bucket")
                .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(41,201,10), 1))
                .build()
            .register()),
    NETHER(SkyMachinaTweaks.register().fluid("liquid_nether", still(), flowing())
            .attributes(a -> a.color(ColorUtils.intColor(137,39,39)))
            .lang("Nether")
            .source(ForgeFlowingFluid.Source::new)
            .attributes(attributes -> attributes.temperature(1300)) //Same as lava
            .bucket()
                .lang("Liquid Nether Bucket")
                .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(137,39,39), 1))
                .build()
            .register()),
    VOID(SkyMachinaTweaks.register().fluid("liquid_void", still(), flowing())
            .attributes(a -> a.color(ColorUtils.intColor(64,38,81)))
            .lang("Void")
            .source(ForgeFlowingFluid.Source::new)
            .bucket()
                .lang("Liquid Void Bucket")
                .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(64,38,81), 1))
                .model(AssetLookup.itemModel("generated_bucket"))
                .build()
            .register());

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
