package io.github.bennyboy1695.skymachinatweaks.register;

import com.simibubi.create.AllTags;
import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.block.Crucible;
import io.github.bennyboy1695.skymachinatweaks.block.TileLessCrucible;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public enum MachinaBlocks {

    UNFIRED_CRUCIBLE(SkyMachinaTweaks.register().block("unfired_crucible", TileLessCrucible::new)
            .initialProperties(Material.CLAY)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .defaultLoot()
            .simpleItem()
            .register()),
    CRUCIBLE(SkyMachinaTweaks.register().block("crucible", Crucible::new)
            .initialProperties(Material.CLAY)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .defaultLoot()
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
