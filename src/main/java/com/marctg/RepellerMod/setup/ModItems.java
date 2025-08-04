package com.marctg.RepellerMod.setup;

import com.marctg.RepellerMod.items.ItemRepeller;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    // Crea el DeferredRegister para items, con el MODID correcto
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "repellermod");

    // Registra el item repeller, el nombre es el que tendrá en el juego
    public static final RegistryObject<Item> REPELLER = ITEMS.register("repeller",
            () -> new ItemRepeller(new Item.Properties().stacksTo(1)));

    // Método para registrar el DeferredRegister al bus de eventos del mod
    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
