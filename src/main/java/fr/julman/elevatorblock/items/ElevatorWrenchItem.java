package fr.julman.elevatorblock.items;

import java.util.List;

import fr.julman.elevatorblock.ElevatorBlockMod;
import fr.julman.elevatorblock.block.ElevatorBlock;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ElevatorWrenchItem extends ModItem {
    public ElevatorWrenchItem() {
        super(new Properties().tab(ElevatorBlockMod.TAB));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown())
            components.add(
                    new TranslatableComponent("item.elevator_block.elevator_wrench.tooltip.shift"));
        else
            components.add(
                    new TranslatableComponent("item.elevator_block.tooltip.default"));
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();

        if (block instanceof ElevatorBlock) {
            ElevatorBlock elevatorBlock = (ElevatorBlock) block;
            
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
