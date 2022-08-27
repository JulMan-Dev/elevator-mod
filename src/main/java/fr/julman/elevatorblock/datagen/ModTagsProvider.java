package fr.julman.elevatorblock.datagen;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import fr.julman.elevatorblock.ElevatorBlockMod;
import fr.julman.elevatorblock.block.ModBlocks;
import fr.julman.elevatorblock.utils.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModTagsProvider {

    public static class Blocks extends BlockTagsProvider {
        public Blocks(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, ElevatorBlockMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags() {
            List<Block> blocks = ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();

            Tag.Builder tag = this.getOrCreateRawBuilder(ModTags.Blocks.ELEVATOR_BLOCKS);

            for (Block block : blocks) {
                tag.addElement(block.getRegistryName(), this.getName());
            }
        }
    }

    public static class Items extends ItemTagsProvider {
        public Items(DataGenerator generator, BlockTagsProvider provider,
                @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, provider, ElevatorBlockMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags() {
            List<Item> items = ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).map(Block::asItem)
                    .toList();

            Tag.Builder tag = this.getOrCreateRawBuilder(ModTags.Items.ELEVATOR_BLOCKS);

            for (Item item : items) {
                tag.addElement(item.getRegistryName(), this.getName());
            }
        }
    }

}
