package io.github.bennyboy1695.skymachinatweaks.register;

import com.oierbravo.createsifter.register.ModTags;
import com.simibubi.create.content.contraptions.itemAssembly.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.compat.createsifter.CustomMesh;
import io.github.bennyboy1695.skymachinatweaks.config.Config;
import io.github.bennyboy1695.skymachinatweaks.util.ColorUtils;
import io.github.bennyboy1695.skymachinatweaks.util.ItemColorImpl;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public enum MachinaItems {

    MANA_MESH(SkyMachinaTweaks.register().item("mana_mesh", properties -> new CustomMesh(properties, Config.COMMON.manaMeshDurability.get(), Config.COMMON.manaMeshTakeDurability.get()))
            .model(AssetLookup.customGenericItemModel("meshes", "mana_mesh"))
            .tag(ModTags.ModItemTags.MESHES.tag)
            .register()),
    BLOOD_MESH(SkyMachinaTweaks.register().item("blood_mesh", properties -> new CustomMesh(properties, Config.COMMON.bloodMeshDurability.get(), Config.COMMON.bloodMeshTakeDurability.get()))
            .model(AssetLookup.customGenericItemModel("meshes", "blood_mesh"))
            .tag(ModTags.ModItemTags.MESHES.tag)
            .register()),
    STEEL_MESH(SkyMachinaTweaks.register().item("steel_mesh", properties -> new CustomMesh(properties, Config.COMMON.steelMeshDurability.get(), Config.COMMON.steelMeshTakeDurability.get()))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(69, 73, 72), 0))
            .model(AssetLookup.customGenericItemModel("meshes", "steel_mesh"))
            .tag(ModTags.ModItemTags.MESHES.tag)
            .register()),
    LEAD_MESH(SkyMachinaTweaks.register().item("lead_mesh", properties -> new CustomMesh(properties, Config.COMMON.leadMeshDurability.get(), Config.COMMON.leadMeshTakeDurability.get()))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(62, 67, 87), 0))
            .model(AssetLookup.customGenericItemModel("meshes", "lead_mesh"))
            .tag(ModTags.ModItemTags.MESHES.tag)
            .register()),
    OMNI_MESH(SkyMachinaTweaks.register().item("omni_mesh", properties -> new CustomMesh(properties, Config.COMMON.omniMeshDurability.get(), Config.COMMON.omniMeshTakeDurability.get()))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(21,191,55), 0))
            .model(AssetLookup.customGenericItemModel("meshes", "omni_mesh"))
            .tag(ModTags.ModItemTags.MESHES.tag)
            .register()),

    DUSTY_ORE_0(SkyMachinaTweaks.register().item("dusty_crushed_ore_l0", Item::new)
            .model(AssetLookup.itemModel("dusty_crushed_ore_l0"))
            .register()),
    DUSTY_ORE_1(SkyMachinaTweaks.register().item("dusty_crushed_ore_l1", Item::new)
            .properties(p -> p.rarity(Rarity.COMMON))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(255,255,255), 0))
            .model(AssetLookup.itemModel("dusty_crushed_ore_l1"))
            .register()),
    DUSTY_ORE_2(SkyMachinaTweaks.register().item("dusty_crushed_ore_l2", Item::new)
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(255,255,85), 0))
            .model(AssetLookup.itemModel("dusty_crushed_ore_l2"))
            .register()),
    DUSTY_ORE_3(SkyMachinaTweaks.register().item("dusty_crushed_ore_l3", Item::new)
            .properties(p -> p.rarity(Rarity.RARE))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(85,255,255), 0))
            .model(AssetLookup.itemModel("dusty_crushed_ore_l3"))
            .register()),
    DUSTY_ORE_4(SkyMachinaTweaks.register().item("dusty_crushed_ore_l4", Item::new)
            .properties(p -> p.rarity(Rarity.EPIC))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(255,85,255), 0))
            .model(AssetLookup.itemModel("dusty_crushed_ore_l4"))
            .register()),
    DUSTY_ORE_5(SkyMachinaTweaks.register().item("dusty_crushed_ore_l5", Item::new)
            .properties(p -> p.rarity(Rarity.create("Legendary", style -> style.withColor(ChatFormatting.DARK_PURPLE))))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(170,0,170), 0))
            .model(AssetLookup.itemModel("dusty_crushed_ore_l5"))
            .register()),
    DUSTY_ORE_6(SkyMachinaTweaks.register().item("dusty_crushed_ore_l6", Item::new)
            .properties(p -> p.rarity(Rarity.create("Omega", style -> style.withColor(ChatFormatting.GOLD))))
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(255,170,0), 0))
            .model(AssetLookup.itemModel("dusty_crushed_ore_l6"))
            .register()),

    RUBBER_DIE(SkyMachinaTweaks.register().item("rubber_die", Item::new)
            .model(AssetLookup.itemModel("rubber_die"))
            .register()),

    SHATTERED_AMETHYST(SkyMachinaTweaks.register().item("shattered_amethyst", Item::new)
            .model(AssetLookup.itemModel("shattered_amethyst"))
            .register()),

    SKY_CORE(SkyMachinaTweaks.register().item("final_sky_core", Item::new)
            .model(AssetLookup.itemModel("final_sky_core"))
            .register()),
    SKY_BASE(SkyMachinaTweaks.register().item("final_sky_base", Item::new)
            .model(AssetLookup.itemModel("final_sky_base"))
            .register()),
    OVERWORLD_CAKE_BASE(SkyMachinaTweaks.register().item("overworld_cake_base", Item::new)
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(76,244,43), 0))
            .model(AssetLookup.itemModel("overworld_cake_base"))
            .register()),
    NETHER_CAKE_BASE(SkyMachinaTweaks.register().item("nether_cake_base", Item::new)
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(197,56,56), 0))
            .model(AssetLookup.itemModel("nether_cake_base"))
            .register()),
    VOID_CAKE_BASE(SkyMachinaTweaks.register().item("void_cake_base", Item::new)
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(105,62,133), 0))
            .model(AssetLookup.itemModel("void_cake_base"))
            .register()),
    INCOMPLETE_OVERWORLD_CAKE(SkyMachinaTweaks.register().item("incomplete_overworld_cake", SequencedAssemblyItem::new)
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(76,244,43), 0))
            .model(AssetLookup.itemModel("incomplete_overworld_cake"))
            .register()),
    INCOMPLETE_NETHER_CAKE(SkyMachinaTweaks.register().item("incomplete_nether_cake", SequencedAssemblyItem::new)
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(197,56,56), 0))
            .model(AssetLookup.itemModel("incomplete_nether_cake"))
            .register()),
    INCOMPLETE_VOID_CAKE(SkyMachinaTweaks.register().item("incomplete_void_cake", SequencedAssemblyItem::new)
            .color(() -> ItemColorImpl.supplier(ColorUtils.intColor(105,62,133), 0))
            .model(AssetLookup.itemModel("incomplete_void_cake"))
            .register()),
    REDSTONE_WIRE(SkyMachinaTweaks.register().item("redstone_wire", Item::new)
            .model(AssetLookup.itemModel("redstone_wire"))
            .register()),
    REDSTONE_SPOOL(SkyMachinaTweaks.register().item("redstone_spool", Item::new)
            .model(AssetLookup.itemModel("redstone_spool"))
            .register()),
    EMPTY_GATE_PEARL(SkyMachinaTweaks.register().item("empty_gate_pearl", Item::new)
            .model(AssetLookup.itemModel("empty_gate_pearl"))
            .register());
    private final ItemEntry<?> itemEntry;
    MachinaItems(ItemEntry<? extends Item> itemEntry) {
        this.itemEntry = itemEntry;
    }

    public ItemEntry<?> getItemEntry() {
        return itemEntry;
    }
}
