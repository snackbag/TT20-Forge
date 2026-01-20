package net.snackbag.tt20.mixin.world;

//? if >=1.21.11 {
/*import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
*///?} else {
import net.minecraft.world.level.GameRules;
//?}
import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.snackbag.tt20.TT20;
import net.snackbag.tt20.util.TPSCalculator;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRules.class)
public abstract class GameRulesMixin {
    //? if >=1.21.11 {
    /*@ModifyReturnValue(method = "get", at = @At("RETURN"))
    private <T> T randomTickSpeedAcceleration(
            T original,
            @Local(argsOnly = true) GameRule<T> rule
    ) {
        if (!TT20.config.enabled() || !TT20.config.randomTickSpeedAcceleration())
            return original;

        if (rule != GameRules.RANDOM_TICK_SPEED)
            return original;

        if (original instanceof Integer intValue) {
            @SuppressWarnings("unchecked")
            T result = (T) Integer.valueOf(
                    (int) (intValue * TPSCalculator.MAX_TPS
                            / (float) TT20.TPS_CALCULATOR.getMostAccurateTPS())
            );
            return result;
        }

        return original;
    }
    *///?} else {
    @ModifyReturnValue(method = "getInt", at = @At("RETURN"))
    private int randomTickSpeedAcceleration(int original, @Local(argsOnly = true) GameRules.Key<GameRules.IntegerValue> rule) {
        if (!TT20.config.enabled() || !TT20.config.randomTickSpeedAcceleration()) return original;
        if (!(rule == GameRules.RULE_RANDOMTICKING)) return original;
        return (int) (original * TPSCalculator.MAX_TPS / (float) TT20.TPS_CALCULATOR.getMostAccurateTPS());
    }
    //?}
}
