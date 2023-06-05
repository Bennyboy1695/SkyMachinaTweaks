package io.github.bennyboy1695.skymachinatweaks.register;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.item.GrassBeater;
import net.minecraft.world.item.Item;

public enum MachinaItems {
    GRASS_BEATER(SkyMachinaTweaks.register().item("grass_beater", GrassBeater::new)
            .register());
    private final ItemEntry<?> itemEntry;

    MachinaItems(ItemEntry<? extends Item> itemEntry) {
        this.itemEntry = itemEntry;
    }

    MachinaItems(String id, boolean assemblyToo) {
        this.itemEntry = SkyMachinaTweaks.register().item(id, Item::new)
                .model(AssetLookup.itemModel(id))
                .register();
        if (assemblyToo) {
            SkyMachinaTweaks.register().item(id + "_incomplete", SequencedAssemblyItem::new)
                    .model(AssetLookup.itemModel(id + "_incomplete"))
                    .register();
        }
    }

    public ItemEntry<?> getItemEntry() {
        return itemEntry;
    }
}
