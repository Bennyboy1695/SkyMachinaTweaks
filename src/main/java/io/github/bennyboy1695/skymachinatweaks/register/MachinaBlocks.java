package io.github.bennyboy1695.skymachinatweaks.register;

import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.block.Crucible;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public enum MachinaBlocks {

    CRUCIBLE(SkyMachinaTweaks.register().block("crucible", Crucible::new)
            .initialProperties(Material.CLAY)

            .simpleItem()
            .register());


    private final BlockEntry<? extends Block> blockEntry;

    MachinaBlocks(BlockEntry<? extends Block> blockEntry) {
        this.blockEntry = blockEntry;
    }

    public BlockEntry<? extends Block> getBlockEntry() {
        return blockEntry;
    }
}
