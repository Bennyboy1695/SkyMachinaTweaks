package io.github.bennyboy1695.skymachinatweaks.item;

import io.github.bennyboy1695.skymachinatweaks.data.recipe.BeaterRecipe;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaRecipeTypes;
import io.github.bennyboy1695.skymachinatweaks.register.ModRecipeTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.Optional;

public class GrassBeater extends Item {

    BeaterWrapper BEATER_WRAPPER = new BeaterWrapper();
    public GrassBeater(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockState state = Blocks.AIR.defaultBlockState();
        try {
            HitResult hitResult = player.pick(5.0, 0.f, true);
            if (hitResult.getType().equals(HitResult.Type.BLOCK)) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                state = level.getBlockState(blockHitResult.getBlockPos());
            }
            BlockState finalState = state;
            BEATER_WRAPPER.setItem(0, finalState.getBlock().asItem().getDefaultInstance());
            Optional<BeaterRecipe> beaterRecipe = ModRecipeTypes.BEATER.find(BEATER_WRAPPER, level);
            beaterRecipe.ifPresent(recipe -> {
                recipe.rollResults().forEach(itemStack -> spawnItem(level, hitResult.getLocation(), itemStack));
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            player.getCooldowns().addCooldown(this, 10);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    private void spawnItem(Level level, Vec3 location, ItemStack stack) {
        level.addFreshEntity(new ItemEntity(level, location.x, location.y, location.z, stack));
    }

    private static class BeaterWrapper extends RecipeWrapper {
        public BeaterWrapper() {
            super(new ItemStackHandler(1));
        }
    }
}
