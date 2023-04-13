package io.github.bennyboy1695.skymachinatweaks.compat.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.compat.kubejs.recipes.CrucibleRecipeJS;
import io.github.bennyboy1695.skymachinatweaks.compat.kubejs.recipes.HeatRecipeJS;

public class SkyMachinaTweaksKubePlugin extends KubeJSPlugin {

    @Override
    public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
        event.register(SkyMachinaTweaks.asResource("heat"), HeatRecipeJS::new);
        event.register(SkyMachinaTweaks.asResource("crucible"), CrucibleRecipeJS::new);
    }
}
