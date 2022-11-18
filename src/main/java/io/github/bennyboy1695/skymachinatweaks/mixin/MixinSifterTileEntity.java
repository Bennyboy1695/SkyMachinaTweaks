package io.github.bennyboy1695.skymachinatweaks.mixin;

import com.oierbravo.createsifter.ModRecipeTypes;
import com.oierbravo.createsifter.content.contraptions.components.sifter.SifterTileEntity;
import com.oierbravo.createsifter.content.contraptions.components.sifter.SiftingRecipe;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import io.github.bennyboy1695.skymachinatweaks.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = SifterTileEntity.class, remap = false)
public abstract class MixinSifterTileEntity extends KineticTileEntity {

    @Shadow
    public ItemStackHandler meshInv;
    @Shadow
    private SiftingRecipe lastRecipe;
    @Shadow
    public int timer;
    @Shadow
    protected CombinedInvWrapper inputAndMeshCombined;
    @Shadow
    public ItemStackHandler inputInv;
    @Shadow
    public ItemStackHandler outputInv;

    @Shadow
    public abstract int getProcessingSpeed();

    @Shadow
    public abstract void spawnParticles();

    public MixinSifterTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    /**
     * @author Bennyboy1695
     * @reason Stop ticking when mesh is broke
     */
    @Overwrite
    public void tick() {
        super.tick();
        if (this.getSpeed() != 0.0F) {
            for (int i = 0; i < this.outputInv.getSlots(); ++i) {
                if (this.outputInv.getStackInSlot(i).getCount() == this.outputInv.getSlotLimit(i)) {
                    return;
                }
            }

            if (this.timer > 0) {
                if (Config.COMMON.shouldMeshesTakeDurabilityInSifter.get()) {
                    if (!meshInv.getStackInSlot(0).isEmpty()) {
                        this.timer -= this.getProcessingSpeed();
                        if (this.level.isClientSide) {
                            this.spawnParticles();
                        } else {
                            if (this.timer <= 0) {
                                this.process();
                            }
                        }
                    }
                } else {
                    this.timer -= this.getProcessingSpeed();
                    if (this.level.isClientSide) {
                        this.spawnParticles();
                    } else {
                        if (this.timer <= 0) {
                            this.process();
                        }
                    }
                }
            } else if (!this.inputInv.getStackInSlot(0).isEmpty()) {
                RecipeWrapper inventoryIn = new RecipeWrapper(this.inputAndMeshCombined);
                if (this.lastRecipe != null && this.lastRecipe.matches(inventoryIn, this.level)) {
                    this.timer = this.lastRecipe.getProcessingDuration();
                    this.sendData();
                } else {
                    Optional<SiftingRecipe> recipe = ModRecipeTypes.SIFTING.find(inventoryIn, this.level);
                    if (!recipe.isPresent()) {
                        this.timer = 100;
                        this.sendData();
                    } else {
                        this.lastRecipe = (SiftingRecipe) recipe.get();
                        this.timer = this.lastRecipe.getProcessingDuration();
                        this.sendData();
                    }

                }
            }
        }
    }

    /**
     * @author Bennyboy1695
     * @reason Attempting to make it stop when the mesh breaks
     */
    @Overwrite
    private void process() {
        RecipeWrapper inventoryIn = new RecipeWrapper(this.inputAndMeshCombined);
        if (this.lastRecipe == null || !this.lastRecipe.matches(inventoryIn, this.level)) {
            Optional<SiftingRecipe> recipe = ModRecipeTypes.SIFTING.find(inventoryIn, this.level);
            if (!recipe.isPresent()) {
                return;
            }

            this.lastRecipe = (SiftingRecipe) recipe.get();
        }

        ItemStack stackInSlot = this.inputInv.getStackInSlot(0);
        stackInSlot.shrink(1);
        this.inputInv.setStackInSlot(0, stackInSlot);
        this.lastRecipe.rollResults().forEach((stack) -> {
            ItemHandlerHelper.insertItemStacked(this.outputInv, stack, false);
        });
        if (Config.COMMON.shouldMeshesTakeDurabilityInSifter.get()) {
            ItemStack mesh = this.meshInv.getStackInSlot(0);
            if (mesh.getDamageValue() < mesh.getMaxDamage()) {
                double chance = Config.COMMON.durabiltyTakeChance.get();
                for (double d = 0.0; chance > 0.0 && d < 1.0; d++) {
                    if (level.random.nextDouble(chance + 0.1) > 0.0) {
                        mesh.hurt(1, level.random, null);
                        this.meshInv.setStackInSlot(0, mesh);
                    }
                }
            } else {
                this.timer = 0;
                this.meshInv.setStackInSlot(0, ItemStack.EMPTY);
                this.inputAndMeshCombined = new CombinedInvWrapper(new IItemHandlerModifiable[]{inputInv, meshInv});
                this.lastRecipe = null;
            }
        }
        this.sendData();
        this.setChanged();
    }
}
