package fr.julman.elevatorblock.block;

import fr.julman.elevatorblock.ElevatorBlockMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class ElevatorBlock extends ModBlock {
    public String color;

    public ElevatorBlock(String color) {
        super(
            Properties.of(Material.WOOL)
                .strength(0.5f, 0.5f)
                .sound(SoundType.WOOL)
        );

        this.color = color;
    }

    public boolean canTeleportTo(ElevatorBlock block, Level world, BlockPos currentPos, BlockPos targetPos) {
        ElevatorBlockMod.LOGGER.info("Trying to teleport from {} (color = {}) from {} (color = {})", currentPos, this.color, targetPos, block.color);

        if (!this.color.equals(block.color))
            return false;

        BlockState state1 = world.getBlockState(targetPos.above(1));
        BlockState state2 = world.getBlockState(targetPos.above(2));

        if (state1.canOcclude() || state2.canOcclude())
            return false;

        return true;
    }
}
