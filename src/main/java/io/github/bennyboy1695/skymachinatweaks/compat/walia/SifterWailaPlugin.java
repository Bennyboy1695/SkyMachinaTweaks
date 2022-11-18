package io.github.bennyboy1695.skymachinatweaks.compat.walia;

import com.oierbravo.createsifter.content.contraptions.components.sifter.SifterBlock;
import com.oierbravo.createsifter.content.contraptions.components.sifter.SifterTileEntity;
import mcp.mobius.waila.api.*;

@WailaPlugin
public class SifterWailaPlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(SifterComponentProvider.INSTANCE, SifterTileEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(SifterComponentProvider.INSTANCE, TooltipPosition.BODY, SifterBlock.class);
    }
}
