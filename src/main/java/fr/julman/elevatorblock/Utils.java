package fr.julman.elevatorblock;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class Utils {
    public static void setActionBarText(Player player, ClientboundSetActionBarTextPacket packet) {
        if (player instanceof LocalPlayer) {
            LocalPlayer localPlayer = (LocalPlayer) player;

            localPlayer.connection.setActionBarText(packet);
        }

        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) player;

            PacketDistributor.PLAYER.with(() -> serverPlayer).send(packet);
        }
    }
}
