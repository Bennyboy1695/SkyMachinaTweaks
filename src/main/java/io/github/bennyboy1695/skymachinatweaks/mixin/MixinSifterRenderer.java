package io.github.bennyboy1695.skymachinatweaks.mixin;

import com.jozufozu.flywheel.core.PartialModel;
import com.oierbravo.createsifter.content.contraptions.components.sifter.SifterRenderer;
import com.oierbravo.createsifter.register.ModPartials;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaPartials;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SifterRenderer.class, remap = false)
public class MixinSifterRenderer {

    @Redirect(method = "renderSafe(Lcom/simibubi/create/content/contraptions/base/KineticTileEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", target = "Lcom/oierbravo/createsifter/register/ModPartials;getFromItemStack(Lnet/minecraft/world/item/ItemStack;)Lcom/jozufozu/flywheel/core/PartialModel;"))
    private PartialModel SMT$renderOursToo(ItemStack itemStack) {
        if (itemStack.getItem().getRegistryName().toString().startsWith("createsifter")) {
            return ModPartials.getFromItemStack(itemStack);
        } else if (itemStack.getItem().getRegistryName().toString().startsWith("skymachinatweaks")) {
            return MachinaPartials.getFromItemStack(itemStack);
        }
        return ModPartials.ANDESITE_MESH;
    }

}
