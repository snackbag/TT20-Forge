package net.snackbag.tt20.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.dedicated.ServerWatchdog;
import net.snackbag.tt20.TT20;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerWatchdog.class)
public abstract class DedicatedServerWatchdogMixin {
    //? if >=1.20.6 {
    @ModifyExpressionValue(method = "run", at = @At(value = "FIELD", target = "Lnet/minecraft/server/dedicated/ServerWatchdog;maxTickTimeNanos:J", opcode = Opcodes.GETFIELD))
    //?} else {
    /*@ModifyExpressionValue(method = "run", at = @At(value = "FIELD", target = "Lnet/minecraft/server/dedicated/ServerWatchdog;maxTickTime:J", opcode = Opcodes.GETFIELD))
    *///?}
    private long cancelServerWatchdog(long original) {
        return TT20.config.serverWatchdog() ? original : Long.MAX_VALUE; // using Long.MAX_VALUE, because we can't cancel the if statement
    }
}