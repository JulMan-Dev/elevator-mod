package fr.julman.elevatorblock.datagen;

import fr.julman.elevatorblock.ElevatorBlockMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = ElevatorBlockMod.MOD_ID, bus = Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(new ModRecipesProvider(generator));
        generator.addProvider(new ModLootTablesProvider(generator));

        ModTagsProvider.Blocks block_tags_provider = new ModTagsProvider.Blocks(generator, helper);

        generator.addProvider(block_tags_provider);
        generator.addProvider(new ModTagsProvider.Items(generator, block_tags_provider, helper));

        generator.addProvider(new ModBlocksStateProviders(generator, helper));
        generator.addProvider(new ModItemsModelProvider(generator, helper));
    }
}
