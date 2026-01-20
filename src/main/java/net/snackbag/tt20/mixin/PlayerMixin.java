package net.snackbag.tt20.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.snackbag.tt20.TT20;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
//? if <=1.21 {
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.snackbag.tt20.util.TPSUtil;
//?}

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Unique
    private boolean TT20$isClient(Entity entity) {
        //? if >=1.20.1 {
        return entity.level().isClientSide();
        //?} else {
        /*return entity.getLevel().isClientSide();
        *///?}
    }

    //? if <1.21 {
    @ModifyReturnValue(method = "getPortalWaitTime", at = @At("RETURN"))
    private int netherPortalTimeTT20(int original) {
        if (!TT20.config.enabled() || !TT20.config.portalAcceleration()) return original;
        if (TT20$isClient((Entity) (Object) this)) return original;
        if (original == 1) return original;

        return TPSUtil.tt20(original, false);
    }
    //?}
    @ModifyExpressionValue(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;sleepCounter:I", opcode = Opcodes.GETFIELD))
    private int tickTT20(int original) {
        if (!TT20.config.enabled() || !TT20.config.sleepingAcceleration()) return original;
        //? if >=1.20.1 {
        if (((Entity) (Object) this).level().isClientSide()) return original;
        //?} else {
        /*if (((Entity) (Object) this).getLevel().isClientSide()) return original;
        *///?}
        /*Player self = (Player)(Object)this;
        if (self.isSleeping()) {*/
        return original + TT20.TPS_CALCULATOR.applicableMissedTicks();
        /*}
        return original;*/
    }
}