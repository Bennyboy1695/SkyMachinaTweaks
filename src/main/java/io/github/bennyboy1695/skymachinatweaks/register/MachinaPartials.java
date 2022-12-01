package io.github.bennyboy1695.skymachinatweaks.register;

import com.jozufozu.flywheel.core.PartialModel;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import net.minecraft.world.item.ItemStack;

public class MachinaPartials {

    private static final PartialModel MANA_MESH = block("meshes/mana_mesh");
    private static final PartialModel BLOOD_MESH = block("meshes/blood_mesh");
    private static final PartialModel LEAD_MESH = block("meshes/lead_mesh");
    private static final PartialModel STEEL_MESH = block("meshes/steel_mesh");
    private static final PartialModel OMNI_MESH = block("meshes/omni_mesh");

    public static void load() {
        // init static fields
    }

    public static PartialModel getFromItemStack(ItemStack itemStack){
        String itemRegistryName = itemStack.getItem().getRegistryName().toString();
        return switch (itemRegistryName) {
            case "skymachinatweaks:mana_mesh" -> MANA_MESH;
            case "skymachinatweaks:blood_mesh" -> BLOOD_MESH;
            case "skymachinatweaks:steel_mesh" -> STEEL_MESH;
            case "skymachinatweaks:lead_mesh" -> LEAD_MESH;
            case "skymachinatweaks:omni_mesh" -> OMNI_MESH;
            default -> MANA_MESH;
        };
    }
    public static PartialModel block(String path) {
        return new PartialModel(SkyMachinaTweaks.asResource("block/" + path));
    }
}
