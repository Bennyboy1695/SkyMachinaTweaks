package io.github.bennyboy1695.skymachinatweaks.block;

import com.simibubi.create.foundation.block.ITE;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.block.tile.CrucibleTileEntity;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.CrucibleRecipe;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Crucible extends Block implements ITE<CrucibleTileEntity> {

    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), BooleanOp.ONLY_FIRST);

    public Crucible(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState p_151964_, BlockGetter p_151965_, BlockPos p_151966_, CollisionContext p_151967_) {
        return SHAPE;
    }

    @Override
    public @NotNull VoxelShape getInteractionShape(BlockState p_151955_, BlockGetter p_151956_, BlockPos p_151957_) {
        return INSIDE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockEntity tileEntity = level.getBlockEntity(pos);

        if (!(tileEntity instanceof CrucibleTileEntity crucible)) {
            return InteractionResult.SUCCESS;
        }

        if (itemStack.getItem() instanceof BucketItem) {
            if (!level.isClientSide()) {
                FluidUtil.interactWithFluidHandler(player, hand, crucible.getOutputTank().getPrimaryHandler());
            }
            return InteractionResult.SUCCESS;
        }

        @Nullable final CrucibleRecipe recipe = crucible.getMeltable();
        if (recipe != null
                && !crucible.getOutputTank().isEmpty()
                && !crucible.getOutputTank().getPrimaryHandler().getFluid().getFluid().isSame(recipe.getResultFluid().getFluid())) {
            return InteractionResult.SUCCESS;
        }

        if (SkyMachinaTweaks.getInstance().recipeCache().isMeltableByItemStack(itemStack)) {
            @Nonnull final ItemStack addStack = itemStack.copy();
            addStack.setCount(1);
            @Nonnull final ItemStack insertStack = crucible.inputInv().insertItem(0, addStack, true);
            if (!ItemStack.matches(addStack, insertStack)) {
                crucible.inputInv().insertItem(0, addStack, false);

                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }
                crucible.setChanged();
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public Class<CrucibleTileEntity> getTileEntityClass() {
        return CrucibleTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends CrucibleTileEntity> getTileEntityType() {
        return MachinaTiles.CRUCIBLE.get();
    }
}
