package fr.julman.elevatorblock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.julman.elevatorblock.block.ModBlocks;
import fr.julman.elevatorblock.events.EventListener;
import fr.julman.elevatorblock.items.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ElevatorBlockMod.MOD_ID)
public class ElevatorBlockMod {
    public static final String MOD_ID = "elevator_block";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final Tab TAB = new Tab();

    public ElevatorBlockMod() {
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
        IEventBus eventBus = context.getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        MinecraftForge.EVENT_BUS.register(new EventListener());
    }
}
