package fr.julman.elevatorblock.block;

import java.util.Objects;
import java.util.function.Supplier;

import fr.julman.elevatorblock.ElevatorBlockMod;
import fr.julman.elevatorblock.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            ElevatorBlockMod.MOD_ID);

    public static final RegistryObject<Block> WHITE_ELEVATOR_BLOCK = registerBlock(
            "white_elevator_block",
            () -> new ElevatorBlock("white"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> ORANGE_ELEVATOR_BLOCK = registerBlock(
            "orange_elevator_block",
            () -> new ElevatorBlock("orange"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> MAGENTA_ELEVATOR_BLOCK = registerBlock(
            "magenta_elevator_block",
            () -> new ElevatorBlock("magenta"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> LIGHT_BLUE_ELEVATOR_BLOCK = registerBlock(
            "light_blue_elevator_block",
            () -> new ElevatorBlock("light_blue"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> YELLOW_ELEVATOR_BLOCK = registerBlock(
            "yellow_elevator_block",
            () -> new ElevatorBlock("yellow"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> LIME_ELEVATOR_BLOCK = registerBlock(
            "lime_elevator_block",
            () -> new ElevatorBlock("lime"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> PINK_ELEVATOR_BLOCK = registerBlock(
            "pink_elevator_block",
            () -> new ElevatorBlock("pink"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> GRAY_ELEVATOR_BLOCK = registerBlock(
            "gray_elevator_block",
            () -> new ElevatorBlock("gray"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> LIGHT_GRAY_ELEVATOR_BLOCK = registerBlock(
            "light_gray_elevator_block",
            () -> new ElevatorBlock("light_gray"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> CYAN_ELEVATOR_BLOCK = registerBlock(
            "cyan_elevator_block",
            () -> new ElevatorBlock("cyan"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> PURPLE_ELEVATOR_BLOCK = registerBlock(
            "purple_elevator_block",
            () -> new ElevatorBlock("purple"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> BLUE_ELEVATOR_BLOCK = registerBlock(
            "blue_elevator_block",
            () -> new ElevatorBlock("blue"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> BROWN_ELEVATOR_BLOCK = registerBlock(
            "brown_elevator_block",
            () -> new ElevatorBlock("brown"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> GREEN_ELEVATOR_BLOCK = registerBlock(
            "green_elevator_block",
            () -> new ElevatorBlock("green"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> RED_ELEVATOR_BLOCK = registerBlock(
            "red_elevator_block",
            () -> new ElevatorBlock("red"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));
    public static final RegistryObject<Block> BLACK_ELEVATOR_BLOCK = registerBlock(
            "black_elevator_block",
            () -> new ElevatorBlock("black"),
            new Item.Properties().tab(ElevatorBlockMod.TAB));

    public static void register(IEventBus eventBus) {
        ElevatorBlockMod.LOGGER.info("Registering blocks...");

        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> supplier,
            Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);

        if (!Objects.isNull(properties))
            registerBlockItem(name, block, properties);

        return block;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
            Item.Properties props) {
        return ModItems.register(name, () -> new BlockItem(block.get(), props));
    }
}
