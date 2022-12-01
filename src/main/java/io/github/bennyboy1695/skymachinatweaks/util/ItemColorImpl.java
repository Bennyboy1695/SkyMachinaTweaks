package io.github.bennyboy1695.skymachinatweaks.util;

import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaksClient;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ItemColorImpl implements ItemColor {

    private final int color;
    private final int layer;

    public ItemColorImpl(int color, int tintIndex) {
        this.color = color;
        this.layer = tintIndex;
    }

    @Override
    public int getColor(ItemStack itemStack, int tintIndex) {
        return this.layer == tintIndex ? color : 0xFFFFFFFF;
    }

    public static Supplier<ItemColor> supplier(int color, int tintIndex) {
        return () -> new ItemColorImpl(color, tintIndex);
    }

    public int getColor() {
        return color;
    }

    public int getLayer() {
        return layer;
    }
}
