package io.github.bennyboy1695.skymachinatweaks.compat.create;

import com.oierbravo.createsifter.CreateSifter;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;

public class MachinaPonders {

    public static void registerTags() {
        PonderRegistry.TAGS.forTag(PonderTag.KINETIC_APPLIANCES).add(CreateSifter.asResource("sifter"));
        PonderRegistry.TAGS.forTag(PonderTag.ARM_TARGETS).add(CreateSifter.asResource("sifter"));
    }
}
