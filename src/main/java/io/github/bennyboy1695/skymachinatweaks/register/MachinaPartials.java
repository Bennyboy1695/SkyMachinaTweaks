package io.github.bennyboy1695.skymachinatweaks.register;

import com.jozufozu.flywheel.core.PartialModel;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import net.minecraft.world.item.ItemStack;

public class MachinaPartials {

    private static final PartialModel MANA_MESH = block("meshes/mana_mesh");
    private static final PartialModel BLOOD_MESH = block("meshes/blood_mesh");

    public static void load() {
        // init static fields
    }

    public static PartialModel getFromItemStack(ItemStack itemStack){
        String itemRegistryName = itemStack.getItem().getRegistryName().toString();
        return switch (itemRegistryName) {
            case "skymachinatweaks:mana_mesh" -> MANA_MESH;
            case "skymachinatweaks:blood_mesh" -> BLOOD_MESH;
            default -> MANA_MESH;
        };
    }
    public static PartialModel block(String path) {
        return new PartialModel(SkyMachinaTweaks.asResource("block/" + path));
    }
}
