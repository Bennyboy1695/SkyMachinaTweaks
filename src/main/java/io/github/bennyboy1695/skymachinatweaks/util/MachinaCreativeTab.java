package io.github.bennyboy1695.skymachinatweaks.util;

import io.github.bennyboy1695.skymachinatweaks.register.MachinaItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MachinaCreativeTab extends CreativeModeTab {

    public MachinaCreativeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return Items.ARROW.getDefaultInstance();
    }
}
