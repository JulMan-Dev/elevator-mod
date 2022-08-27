package fr.julman.elevatorblock.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import fr.julman.elevatorblock.ElevatorBlockMod;
import fr.julman.elevatorblock.block.ModBlocks;
import fr.julman.elevatorblock.items.ModItems;
import fr.julman.elevatorblock.utils.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {
        public ModRecipesProvider(DataGenerator generator) {
                super(generator);
        }

        @Override
        protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
                ShapedRecipeBuilder.shaped(ModItems.ELEVATOR_WRENCH.get(), 1)
                                .pattern("A")
                                .pattern("X")
                                .pattern("X")
                                .define('A', ModItems.TELEPORT_CORE.get())
                                .define('X', Items.STICK)
                                .unlockedBy("has_teleport_core",
                                                inventoryTrigger(
                                                                ItemPredicate.Builder.item().of(
                                                                                ModItems.TELEPORT_CORE.get())
                                                                                .build()))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(ModItems.TELEPORT_CORE.get(), 1)
                                .pattern("X X")
                                .pattern(" A ")
                                .pattern("X X")
                                .define('A', Items.ENDER_PEARL)
                                .define('X', Items.COPPER_INGOT)
                                .unlockedBy("has_ender_pearl",
                                                inventoryTrigger(
                                                                ItemPredicate.Builder.item().of(
                                                                                Items.ENDER_PEARL)
                                                                                .build()))
                                .unlockedBy("has_copper_ingot",
                                                inventoryTrigger(
                                                                ItemPredicate.Builder.item().of(
                                                                                Items.COPPER_INGOT)
                                                                                .build()))
                                .save(consumer);

                Map<Item, Item> elevator_blocks_base = new HashMap<>();

                elevator_blocks_base.put(Items.WHITE_DYE, ModBlocks.WHITE_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.ORANGE_DYE, ModBlocks.ORANGE_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.MAGENTA_DYE, ModBlocks.MAGENTA_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.LIGHT_BLUE_DYE, ModBlocks.LIGHT_BLUE_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.YELLOW_DYE, ModBlocks.YELLOW_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.LIME_DYE, ModBlocks.LIME_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.PINK_DYE, ModBlocks.PINK_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.GRAY_DYE, ModBlocks.GRAY_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.LIGHT_GRAY_DYE, ModBlocks.LIGHT_GRAY_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.CYAN_DYE, ModBlocks.CYAN_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.PURPLE_DYE, ModBlocks.PURPLE_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.BLUE_DYE, ModBlocks.BLUE_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.BROWN_DYE, ModBlocks.BROWN_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.GREEN_DYE, ModBlocks.GREEN_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.RED_DYE, ModBlocks.RED_ELEVATOR_BLOCK.get().asItem());
                elevator_blocks_base.put(Items.BLACK_DYE, ModBlocks.BLACK_ELEVATOR_BLOCK.get().asItem());

                for (Map.Entry<Item, Item> entry : elevator_blocks_base.entrySet()) {
                        ShapelessRecipeBuilder.shapeless(entry.getValue(), 2)
                                        .requires(ItemTags.WOOL)
                                        .requires(entry.getKey())
                                        .requires(entry.getKey())
                                        .requires(ModItems.TELEPORT_CORE.get())
                                        .unlockedBy("has_dye",
                                                        inventoryTrigger(ItemPredicate.Builder.item().of(entry.getKey())
                                                                        .build()))
                                        .save(consumer, new ResourceLocation(
                                                        ElevatorBlockMod.MOD_ID,
                                                        entry.getValue().getRegistryName().getPath() + "_from_wool"));

                        ShapelessRecipeBuilder.shapeless(entry.getValue(), 1)
                                        .requires(ModTags.Items.ELEVATOR_BLOCKS)
                                        .requires(entry.getKey())
                                        .unlockedBy("has_dye",
                                                        inventoryTrigger(ItemPredicate.Builder.item().of(entry.getKey())
                                                                        .build()))
                                        .save(consumer, new ResourceLocation(
                                                        ElevatorBlockMod.MOD_ID,
                                                        entry.getValue().getRegistryName().getPath() + "_from_dyeing"));
                }
        }
}
