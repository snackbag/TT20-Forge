package net.snackbag.tt20.mixin;

import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
//? if >=1.20.6 {
import net.minecraft.server.network.CommonListenerCookie;
//?}
import net.minecraft.server.players.PlayerList;
import net.snackbag.tt20.ModUpdater;
import net.snackbag.tt20.TT20;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerList.class)
public abstract class ServerPlayerListMixin {
    @Inject(method = "placeNewPlayer", at = @At("TAIL"))

    //? if >=1.20.6 {
    private void sendPlayerUpdateMessageIfCorrectPermissions(Connection connection, ServerPlayer player, CommonListenerCookie listener, CallbackInfo ci) {
    //?} else {
    /*private void sendPlayerUpdateMessageIfCorrectPermissions(Connection connection, ServerPlayer player, CallbackInfo ci) {
        *///?}
        if (!TT20.config.automaticUpdater() || !ModUpdater.hasUpdate) return;

        //? if >=1.21.9 {
        if (Objects.requireNonNull(((ServerPlayerMixin) player).getServer()).getPlayerList().isOp(player.nameAndId())) {
        //?} else {
        /*if (Objects.requireNonNull(player.getServer()).getPlayerList().isOp(player.getGameProfile())) {
        *///?}
            player.sendSystemMessage(Component.literal(ModUpdater.updateMessage));
            player.sendSystemMessage(Component.literal("§oOnly operators can see this message"));
        }
    }
}
