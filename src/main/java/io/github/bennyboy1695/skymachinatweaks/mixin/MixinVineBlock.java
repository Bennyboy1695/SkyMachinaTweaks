package io.github.bennyboy1695.skymachinatweaks.mixin;

import io.github.bennyboy1695.skymachinatweaks.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import java.util.Random;

@Mixin(value = VineBlock.class)
public abstract class MixinVineBlock extends Block {

    public MixinVineBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Shadow
    public static BooleanProperty getPropertyForFace(Direction p_57884_) {
        return null;
    }

    @Shadow
    protected abstract boolean canSpread(BlockGetter p_57851_, BlockPos p_57852_);

    @Shadow
    public static boolean isAcceptableNeighbour(BlockGetter p_57854_, BlockPos p_57855_, Direction p_57856_) {
        return false;
    }

    @Shadow
    protected abstract boolean canSupportAtFace(BlockGetter p_57888_, BlockPos p_57889_, Direction p_57890_);

    @Shadow
    protected abstract boolean hasHorizontalConnection(BlockState p_57912_);

    @Shadow
    protected abstract BlockState copyRandomFaces(BlockState p_57871_, BlockState p_57872_, Random p_57873_);

    @Shadow
    @Final
    public static BooleanProperty UP;

    /**
     * @author Bennyboy1695
     * @reason Limit vine growth
     */
    @Overwrite
    public void randomTick(BlockState p_57892_, ServerLevel p_57893_, BlockPos p_57894_, Random p_57895_) {
        if (p_57893_.random.nextInt(4) == 0 && p_57893_.isAreaLoaded(p_57894_, 4)) { // Forge: check area to prevent loading unloaded chunks
            Direction direction = Direction.getRandom(p_57895_);
            BlockPos blockpos = p_57894_.above();
            if (!hasReachedMax(p_57894_, p_57893_)) {
                if (direction.getAxis().isHorizontal() && !p_57892_.getValue(getPropertyForFace(direction))) {
                    if (this.canSpread(p_57893_, p_57894_)) {
                        BlockPos blockpos4 = p_57894_.relative(direction);
                        BlockState blockstate4 = p_57893_.getBlockState(blockpos4);
                        if (blockstate4.isAir()) {
                            Direction direction3 = direction.getClockWise();
                            Direction direction4 = direction.getCounterClockWise();
                            boolean flag = p_57892_.getValue(getPropertyForFace(direction3));
                            boolean flag1 = p_57892_.getValue(getPropertyForFace(direction4));
                            BlockPos blockpos2 = blockpos4.relative(direction3);
                            BlockPos blockpos3 = blockpos4.relative(direction4);
                            if (flag && isAcceptableNeighbour(p_57893_, blockpos2, direction3)) {
                                p_57893_.setBlock(blockpos4, this.defaultBlockState().setValue(getPropertyForFace(direction3), Boolean.valueOf(true)), 2);
                            } else if (flag1 && isAcceptableNeighbour(p_57893_, blockpos3, direction4)) {
                                p_57893_.setBlock(blockpos4, this.defaultBlockState().setValue(getPropertyForFace(direction4), Boolean.valueOf(true)), 2);
                            } else {
                                Direction direction1 = direction.getOpposite();
                                if (flag && p_57893_.isEmptyBlock(blockpos2) && isAcceptableNeighbour(p_57893_, p_57894_.relative(direction3), direction1)) {
                                    p_57893_.setBlock(blockpos2, this.defaultBlockState().setValue(getPropertyForFace(direction1), Boolean.valueOf(true)), 2);
                                } else if (flag1 && p_57893_.isEmptyBlock(blockpos3) && isAcceptableNeighbour(p_57893_, p_57894_.relative(direction4), direction1)) {
                                    p_57893_.setBlock(blockpos3, this.defaultBlockState().setValue(getPropertyForFace(direction1), Boolean.valueOf(true)), 2);
                                } else if ((double) p_57895_.nextFloat() < 0.05D && isAcceptableNeighbour(p_57893_, blockpos4.above(), Direction.UP)) {
                                    p_57893_.setBlock(blockpos4, this.defaultBlockState().setValue(UP, Boolean.valueOf(true)), 2);
                                }
                            }
                        } else if (isAcceptableNeighbour(p_57893_, blockpos4, direction)) {
                            p_57893_.setBlock(p_57894_, p_57892_.setValue(getPropertyForFace(direction), Boolean.valueOf(true)), 2);
                        }

                    }
                } else {
                    if (direction == Direction.UP && p_57894_.getY() < p_57893_.getMaxBuildHeight() - 1) {
                        if (this.canSupportAtFace(p_57893_, p_57894_, direction)) {
                            p_57893_.setBlock(p_57894_, p_57892_.setValue(UP, Boolean.valueOf(true)), 2);
                            return;
                        }

                        if (p_57893_.isEmptyBlock(blockpos)) {
                            if (!this.canSpread(p_57893_, p_57894_)) {
                                return;
                            }

                            BlockState blockstate3 = p_57892_;

                            for (Direction direction2 : Direction.Plane.HORIZONTAL) {
                                if (p_57895_.nextBoolean() || !isAcceptableNeighbour(p_57893_, blockpos.relative(direction2), direction2)) {
                                    blockstate3 = blockstate3.setValue(getPropertyForFace(direction2), Boolean.valueOf(false));
                                }
                            }

                            if (this.hasHorizontalConnection(blockstate3)) {
                                p_57893_.setBlock(blockpos, blockstate3, 2);
                            }

                            return;
                        }
                    }

                    if (p_57894_.getY() > p_57893_.getMinBuildHeight()) {
                        BlockPos blockpos1 = p_57894_.below();
                        BlockState blockstate = p_57893_.getBlockState(blockpos1);
                        if (blockstate.isAir() || blockstate.is(this)) {
                            BlockState blockstate1 = blockstate.isAir() ? this.defaultBlockState() : blockstate;
                            BlockState blockstate2 = this.copyRandomFaces(p_57892_, blockstate1, p_57895_);
                            if (blockstate1 != blockstate2 && this.hasHorizontalConnection(blockstate2)) {
                                p_57893_.setBlock(blockpos1, blockstate2, 2);
                            }
                        }
                    }

                }
            }
        }
    }

    private boolean hasReachedMax(BlockPos pos, ServerLevel level) {
        int count = 1;
        BlockPos lastPos = pos.above();
        while (count < Config.COMMON.maxVineLength.get()) {
            if (level.getBlockState(lastPos).is(Blocks.VINE)) {
                lastPos = lastPos.above();
                count++;
            } else {
                break;
            }
        }
        return count == Config.COMMON.maxVineLength.get();
    }
}
