package fr.julman.elevatorblock.datagen;

import java.util.List;

import fr.julman.elevatorblock.ElevatorBlockMod;
import fr.julman.elevatorblock.block.ModBlocks;
import fr.julman.elevatorblock.items.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemsModelProvider extends ItemModelProvider {
    public ModItemsModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ElevatorBlockMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.simpleItem(ModItems.TELEPORT_CORE.get());
        this.handheldItem(ModItems.ELEVATOR_WRENCH.get());

        List<Block> blocks = ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();

        for (Block block : blocks)
            this.simpleBlock(block);
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(
                item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture(
                        "layer0",
                        new ResourceLocation(ElevatorBlockMod.MOD_ID, "item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(
                item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture(
                        "layer0",
                        new ResourceLocation(ElevatorBlockMod.MOD_ID, "item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder simpleBlock(Block block) {
        ResourceLocation location = block.getRegistryName();

        return withExistingParent(location.getPath(),
                new ResourceLocation(
                        location.getNamespace(),
                        "block/" + location.getPath()));
    }
}
