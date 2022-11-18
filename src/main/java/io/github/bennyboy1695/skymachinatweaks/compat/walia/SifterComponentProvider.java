package io.github.bennyboy1695.skymachinatweaks.compat.walia;

import com.oierbravo.createsifter.content.contraptions.components.sifter.SifterTileEntity;
import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IServerDataProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import mcp.mobius.waila.api.ui.IElementHelper;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public enum SifterComponentProvider implements IComponentProvider, IServerDataProvider<BlockEntity> {
    INSTANCE;

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        CompoundTag data = blockAccessor.getServerData();
        ItemStack mesh = blockAccessor.getServerData().getBoolean("hasMesh") ? ForgeRegistries.ITEMS.getValue(new ResourceLocation(blockAccessor.getServerData().getString("mesh").split(":")[0], blockAccessor.getServerData().getString("mesh").split(":")[1])).getDefaultInstance() : ItemStack.EMPTY;
        TranslatableComponent one = new TranslatableComponent("skymachinatweaks.waila.sifter.mesh");
        Component colon = new TextComponent(": ");
        TranslatableComponent meshText = (blockAccessor.getServerData().getBoolean("hasMesh")
                        ? new TranslatableComponent(mesh.getDescriptionId())
                        : new TranslatableComponent("skymachinatweaks.waila.sifter.no_mesh"));
        iTooltip.add(0, one.append(colon).append(meshText));
        IElementHelper helper = iTooltip.getElementHelper();
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean b) {
        SifterTileEntity sifterTileEntity = (SifterTileEntity) blockEntity;
        compoundTag.putBoolean("hasMesh", sifterTileEntity.hasMesh());
        if (sifterTileEntity.hasMesh()) {
            ItemStack mesh = sifterTileEntity.meshInv.getStackInSlot(0);
            compoundTag.putString("mesh", mesh.getItem().getRegistryName().toString());
            compoundTag.putInt("durability", mesh.getDamageValue());
            compoundTag.putInt("maxDurability", mesh.getMaxDamage());
        }
    }
}
