package io.github.bennyboy1695.skymachinatweaks.mixin;

import com.oierbravo.createsifter.content.contraptions.components.meshes.*;
import com.oierbravo.createsifter.content.contraptions.components.sifter.SiftingRecipe;
import io.github.bennyboy1695.skymachinatweaks.config.Config;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fluids.IFluidBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

import static com.oierbravo.createsifter.content.contraptions.components.meshes.BaseMesh.spawnParticles;

@Mixin(BaseMesh.class)
public class MixinMeshes {

    /**
     * @author Bennyboy1695
     * @reason Fixing it immediately breaking the item instead of just damaging it
     */
    @Overwrite
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        if (!(entityLiving instanceof Player player)) {
            return stack;
        } else {
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.contains("Sifting")) {
                ItemStack toSift = ItemStack.of(tag.getCompound("Sifting"));
                Block blockUnderPlayer = player.getFeetBlockState().getBlock();
                boolean waterlogged = blockUnderPlayer instanceof LiquidBlock || blockUnderPlayer instanceof IFluidBlock;
                List<ItemStack> sifted = SiftingRecipe.applyHandSift(worldIn, entityLiving.position(), toSift, stack, waterlogged);
                if (worldIn.isClientSide) {
                    spawnParticles(entityLiving.getEyePosition(1.0F).add(entityLiving.getLookAngle().scale(0.5)), toSift, worldIn);
                    return stack;
                }

                if (!sifted.isEmpty()) {
                    sifted.forEach((outputStack) -> {
                        if (player instanceof FakePlayer) {
                            player.drop(outputStack, false, false);
                        } else {
                            player.getInventory().placeItemBackInInventory(outputStack);
                        }

                    });
                }

                tag.remove("Sifting");
                if (stack.getDamageValue() < stack.getMaxDamage()) {
                    stack.hurt(1, player.getRandom(), (player instanceof ServerPlayer ? (ServerPlayer) player : null));
                } else {
                    stack.hurtAndBreak(1, entityLiving, (p) -> {
                        p.broadcastBreakEvent(p.getUsedItemHand());
                    });
                }
            }

            return stack;
        }
    }

    @Mixin(StringMesh.class)
    public abstract static class MixinStringMesh extends BaseMesh {

        public MixinStringMesh(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public int getMaxDamage(ItemStack stack) {
            return Config.COMMON.stringMeshDurability.get();
        }


        @Override
        public boolean isDamageable(ItemStack stack) {
            return Config.COMMON.stringMeshTakeDurability.get();
        }
    }

    @Mixin(AndesiteMesh.class)
    public abstract static class MixinAndesiteMesh extends BaseMesh {

        public MixinAndesiteMesh(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public int getMaxDamage(ItemStack stack) {
            return Config.COMMON.andesiteMeshDurability.get();
        }


        @Override
        public boolean isDamageable(ItemStack stack) {
            return Config.COMMON.andesiteMeshTakeDurability.get();
        }
    }

    @Mixin(ZincMesh.class)
    public abstract static class MixinZincMesh extends BaseMesh {

        public MixinZincMesh(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public int getMaxDamage(ItemStack stack) {
            return Config.COMMON.zincMeshDurability.get();
        }


        @Override
        public boolean isDamageable(ItemStack stack) {
            return Config.COMMON.zincMeshTakeDurability.get();
        }
    }

    @Mixin(BrassMesh.class)
    public abstract static class MixinBrassMesh extends BaseMesh {

        public MixinBrassMesh(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public int getMaxDamage(ItemStack stack) {
            return Config.COMMON.brassMeshDurability.get();
        }


        @Override
        public boolean isDamageable(ItemStack stack) {
            return Config.COMMON.brassMeshTakeDurability.get();
        }
    }
}
