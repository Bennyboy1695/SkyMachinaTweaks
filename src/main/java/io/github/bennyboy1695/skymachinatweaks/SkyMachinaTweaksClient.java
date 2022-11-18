package io.github.bennyboy1695.skymachinatweaks;

import io.github.bennyboy1695.skymachinatweaks.compat.create.MachinaPonders;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaPartials;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class SkyMachinaTweaksClient {

    public static void onClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        MachinaPartials.load();
        modEventBus.addListener(SkyMachinaTweaksClient::clientInit);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        MachinaPonders.registerTags();
    }
}
