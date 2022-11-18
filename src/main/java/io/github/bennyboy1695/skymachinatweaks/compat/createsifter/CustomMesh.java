package io.github.bennyboy1695.skymachinatweaks.compat.createsifter;

import com.oierbravo.createsifter.content.contraptions.components.meshes.BaseMesh;
import com.oierbravo.createsifter.content.contraptions.components.meshes.MeshTypes;
import net.minecraft.world.item.ItemStack;

public class CustomMesh extends BaseMesh {

    private final int durability;
    private final boolean takeDamage;

    public CustomMesh(Properties pProperties, int durability, boolean takeDamage) {
        super(pProperties);
        this.durability = durability;
        this.takeDamage = takeDamage;
        this.mesh = MeshTypes.BRASS;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return takeDamage;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return durability;
    }
}
