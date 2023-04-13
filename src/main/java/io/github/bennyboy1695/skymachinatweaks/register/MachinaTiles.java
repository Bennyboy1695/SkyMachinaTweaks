package io.github.bennyboy1695.skymachinatweaks.register;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.block.tile.CrucibleRenderer;
import io.github.bennyboy1695.skymachinatweaks.block.tile.CrucibleTileEntity;

public class MachinaTiles {

    public static final BlockEntityEntry<CrucibleTileEntity> CRUCIBLE = SkyMachinaTweaks.register()
            .tileEntity("crucible", CrucibleTileEntity::new)
            .renderer(() -> CrucibleRenderer::new)
            .validBlocks(MachinaBlocks.CRUCIBLE.getBlockEntry())
            .register();

    public static void register() {

    }
}
