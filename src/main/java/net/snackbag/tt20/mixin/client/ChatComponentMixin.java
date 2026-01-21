package net.snackbag.tt20.mixin.client;

//? if >=1.21.11 {
/*import net.minecraft.client.gui.Font;
*///?}

//? if >=1.20.1 {
import net.minecraft.client.gui.GuiGraphics;
//?} else {
/*import com.mojang.blaze3d.vertex.PoseStack;
*///?}
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.snackbag.tt20.TT20;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    @Shadow
    public abstract void addMessage(Component message);

    @Inject(method = "render", at = @At("HEAD"))
    //? if >=1.21.11 {
    /*private void onPlayerConnectWarn(GuiGraphics context, Font font, int tickCount, int mouseX, int mouseY, boolean focused, boolean changeCursorOnInsertions, CallbackInfo ci) {
    *///?} else if >=1.20.6 {
    private void onPlayerConnectWarn(GuiGraphics context, int currentTick, int mouseX, int mouseY, boolean isChatOpen, CallbackInfo ci) {
    //?} else if >=1.20.1 {
    /*private void onPlayerConnectWarn(GuiGraphics context, int currentTick, int mouseX, int mouseY, CallbackInfo ci) {
    *///?} else {
    /*private void onPlayerConnectWarn(PoseStack p_93781_, int p_93782_, CallbackInfo ci) {
    *///?}

        if (TT20.warned || !TT20.config.singlePlayerWarning()) return;
        addMessage(Component.literal("§c§lCritical incompatibilities found!\n\n§c§6TT20 §cis not stable on singleplayer and you may find yourself having unwanted side effects. You can disable each feature in the config if it gets too annoying."));
        TT20.warned = true;
    }
}
