package io.github.bennyboy1695.skymachinatweaks.register;

import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.block.FlowerPile;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public enum MachinaBlocks {

    FLOWER_PILE(SkyMachinaTweaks.register().block("flower_pile", FlowerPile::new)
            .simpleItem()
            .properties(p -> p.sound(SoundType.GRASS))
            .properties(p -> p.strength(0.1f))
            .initialProperties(Material.DIRT)
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register()),
    MANA_SAND(SkyMachinaTweaks.register().block("mana_sand", properties -> new SandBlock(MaterialColor.COLOR_LIGHT_BLUE.col, properties))
            .simpleItem()
            .properties(p -> p.sound(SoundType.SAND))
            .properties(properties -> properties.strength(0.5f))
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register()),
    BLOOD_SOUL_SOIL(SkyMachinaTweaks.register().block("blood_soul_soil", properties -> new SandBlock(MaterialColor.COLOR_RED.col, properties))
            .simpleItem()
            .properties(p -> p.sound(SoundType.SOUL_SOIL))
            .properties(properties -> properties.strength(0.5f))
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register()),
    INDUSTRIAL__CHASSIS(SkyMachinaTweaks.register().block("industrial_chassis", Block::new)
            .simpleItem()
            .properties(p -> p.sound(SoundType.METAL))
            .initialProperties(Material.HEAVY_METAL)
            .properties(properties -> properties.strength(0.6f))
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register()),
    MANA_CHASSIS(SkyMachinaTweaks.register().block("mana_chassis", Block::new)
            .simpleItem()
            .addLayer(() -> RenderType::cutout)
            .properties(p -> p.sound(SoundType.METAL))
            .initialProperties(Material.HEAVY_METAL)
            .properties(properties -> properties.strength(0.6f))
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register()),
    BLOOD_CHASSIS(SkyMachinaTweaks.register().block("blood_chassis", Block::new)
            .simpleItem()
            .properties(p -> p.sound(SoundType.METAL))
            .initialProperties(Material.HEAVY_METAL)
            .properties(properties -> properties.strength(0.6f))
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register()),
    MAGITECH_CHASSIS(SkyMachinaTweaks.register().block("magitech_chassis", Block::new)
            .simpleItem()
            .properties(p -> p.sound(SoundType.METAL))
            .initialProperties(Material.HEAVY_METAL)
            .properties(properties -> properties.strength(0.6f))
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register()),
    CRUSHED_ENDSTONE(SkyMachinaTweaks.register().block("crushed_endstone", GravelBlock::new)
            .simpleItem()
            .properties(p -> p.sound(SoundType.GRAVEL))
            .initialProperties(Material.DIRT)
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register()),
    CRUSHED_NETHERRACK(SkyMachinaTweaks.register().block("crushed_netherrack", GravelBlock::new)
            .simpleItem()
            .properties(p -> p.sound(SoundType.GRAVEL))
            .initialProperties(Material.DIRT)
            .blockstate((gen, block) -> block.simpleBlock(gen.getEntry(), AssetLookup.partialBaseModel(gen, block)))
            .register());


    private final BlockEntry<? extends Block> blockEntry;
    MachinaBlocks(BlockEntry<? extends Block> blockEntry) {
        this.blockEntry = blockEntry;
    }

    public BlockEntry<? extends Block> getBlockEntry() {
        return blockEntry;
    }
}
