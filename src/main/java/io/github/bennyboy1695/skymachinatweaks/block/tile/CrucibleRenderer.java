package io.github.bennyboy1695.skymachinatweaks.block.tile;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.Mth;
import net.minecraftforge.fluids.FluidStack;


public class CrucibleRenderer extends SafeBlockEntityRenderer<CrucibleTileEntity> {

    public CrucibleRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    protected void renderSafe(CrucibleTileEntity te, float partialTicks, PoseStack ms, MultiBufferSource bufferSource, int light, int overlay) {
        if (!te.getOutputTank().isEmpty()) {
            ms.pushPose();
            renderFluid(te, partialTicks, ms, bufferSource, light, overlay);
            ms.popPose();
        }
        if (!te.inputInv().isEmpty()) {
            ms.pushPose();
            renderItem(te, partialTicks, ms, bufferSource, light, overlay);
            ms.popPose();
        }
    }

    private void renderFluid(CrucibleTileEntity te, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        FluidStack fluidStack = te.getOutputTank().getPrimaryTank().getRenderedFluid();
        float fluidLevel = Mth.clamp(te.getOutputTank().getPrimaryTank().getFluidLevel().getValue() / 4000, 0, 4);
        fluidLevel = 2 - ((1 - fluidLevel) * (1 - fluidLevel));

        float xMin = 2 / 16f;
        float xMax = 2 / 16f;
        final float yMin = 2 / 16f;
        final float yMax = yMin + 12 / 16f * fluidLevel;
        final float zMin = 2 / 16f;
        final float zMax = 14 / 16f;
        TransformStack msr = TransformStack.cast(ms);
        msr.rotateZ(90);
        msr.translateY(-1.0);
        float height = (te.getOutputTank().getPrimaryTank().getTotalUnits(partialTicks) / 1000f) / 10.0f;
        msr.translateX(height + 0.4);
        FluidRenderer.renderFluidBox(fluidStack, xMin, yMin, zMin, xMax, yMax, zMax, buffer, ms, light, false);
    }

    private void renderItem(CrucibleTileEntity te, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemTransforms.TransformType transformType = ItemTransforms.TransformType.HEAD;
        float scale = 0.75f;
        ms.scale(scale, 0.1f, scale);
        float progress = te.solidAmount / 30.0f;
        ms.translate(+0.67, progress + 0.5, +0.67);
        itemRenderer.renderStatic(te.currentItem(), transformType, light, overlay, ms, buffer, 0);
    }

    @Override
    public int getViewDistance() {
        return 16;
    }
}
