package io.github.bennyboy1695.skymachinatweaks.util;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BlockColorImpl implements BlockColor {

    private final int color;
    private final int layer;

    public BlockColorImpl(int color, int tintIndex) {
        this.color = color;
        this.layer = tintIndex;
    }

    public static Supplier<BlockColor> supplier(int color, int tintIndex) {
        return () -> new BlockColorImpl(color, tintIndex);
    }

    @Override
    public int getColor(BlockState p_92567_, @Nullable BlockAndTintGetter p_92568_, @Nullable BlockPos p_92569_, int tintIndex) {
        return this.layer == tintIndex ? color : 0xFFFFFFFF;
    }
}
