package io.github.bennyboy1695.skymachinatweaks.mixin;

import com.oierbravo.createsifter.content.contraptions.components.sifter.SiftingRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = SiftingRecipe.class, remap = false)
public abstract class MixinSiftingRecipe {

    @Shadow public abstract boolean isWaterlogged();


    @Shadow public abstract ItemStack getSiftableItemStack();

    @Shadow public abstract ItemStack getMeshItemStack();

    /**
     * @author Bennyboy1695
     * @reason fix issue with mesh durability
     */
    @Overwrite
    public boolean matches(RecipeWrapper inv, Level worldIn, boolean waterlogged) {
        if (inv.isEmpty()) {
            return false;
        } else if (this.isWaterlogged() != waterlogged) {
            return false;
        } else {
            return getSiftableItemStack().is(inv.getItem(0).getItem()) && this.getMeshItemStack().is(inv.getItem(1).getItem());
        }
    }
}
