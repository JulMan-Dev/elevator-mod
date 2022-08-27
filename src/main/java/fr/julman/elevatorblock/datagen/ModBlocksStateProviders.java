package fr.julman.elevatorblock.datagen;

import java.util.List;

import fr.julman.elevatorblock.ElevatorBlockMod;
import fr.julman.elevatorblock.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocksStateProviders extends BlockStateProvider {

    public ModBlocksStateProviders(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ElevatorBlockMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        List<Block> blocks = ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();

        for (Block block : blocks)
            this.simpleBlock(block);
    }

}
