package io.github.bennyboy1695.skymachinatweaks;

import com.oierbravo.createsifter.register.ModBlocks;
import io.github.bennyboy1695.skymachinatweaks.compat.create.MachinaPonders;
import io.github.bennyboy1695.skymachinatweaks.compat.createsifter.CustomMesh;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaItems;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaPartials;
import io.github.bennyboy1695.skymachinatweaks.util.BlockColorImpl;
import io.github.bennyboy1695.skymachinatweaks.util.ColorUtils;
import io.github.bennyboy1695.skymachinatweaks.util.ItemColorImpl;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkyMachinaTweaksClient {

    public static void onClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        MachinaPartials.load();
        modEventBus.addListener(SkyMachinaTweaksClient::clientInit);
        modEventBus.addListener(SkyMachinaTweaksClient::onBlockColor);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        MachinaPonders.registerTags();
    }

    public static void onBlockColor(ColorHandlerEvent.Block event) {
        event.getBlockColors().register(new BlockColorImpl(ColorUtils.intColor(69, 73, 72), 0), ModBlocks.SIFTER.get());
        event.getBlockColors().register(new BlockColorImpl(ColorUtils.intColor(62, 67, 87), 0), ModBlocks.SIFTER.get());
        event.getBlockColors().register(new BlockColorImpl(ColorUtils.intColor(21,191,55), 0), ModBlocks.SIFTER.get());
    }
}
