package fr.julman.elevatorblock.items;

import java.util.Collection;

import com.google.common.base.Supplier;

import fr.julman.elevatorblock.ElevatorBlockMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            ElevatorBlockMod.MOD_ID);

    public static final RegistryObject<Item> TELEPORT_CORE = ModItems.register("teleport_core",
            () -> new TeleportCoreItem());
    public static final RegistryObject<Item> ELEVATOR_WRENCH = ModItems.register("elevator_wrench",
            () -> new ElevatorWrenchItem());

    public static void register(IEventBus eventBus) {
        ElevatorBlockMod.LOGGER.info("Registering items...");

        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> register(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    public static RegistryObject<Item> find(String name) {
        Collection<RegistryObject<Item>> items = ITEMS.getEntries();

        for (RegistryObject<Item> item : items) {
            if (item.getId().getPath().equals(name))
                return item;
        }

        return null;
    }
}