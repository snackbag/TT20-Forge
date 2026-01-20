package net.snackbag.tt20;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.snackbag.tt20.util.TPSUtil;

public class MainCommand {
    public MainCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("tt20")
                        .then(Commands.literal("tps").executes(ctx -> executeTps(ctx, true)))
                        .then(Commands.literal("status").executes(MainCommand::executeStatus))
                        .then(Commands.literal("toggle")
                                //? if >=1.21.11 {
                                /*.requires(Commands.hasPermission(Commands.LEVEL_ADMINS))
                                *///?} else {
                                .requires(src -> src.hasPermission(3))
                                //?}
                                .executes(MainCommand::executeToggle))
                        .then(Commands.literal("reload")
                                //? if >=1.21.11 {
                                /*.requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                *///?} else {
                                .requires(src -> src.hasPermission(2))
                                //?}
                                .executes(MainCommand::executeReload))
        );
    }

    private static int executeReload(CommandContext<CommandSourceStack> ctx) {
        var source = ctx.getSource();

        TT20.config.reload();
        //? if >=1.20.1 {
        source.sendSuccess(() -> Component.literal("Reloaded config"), false);
        //?} else {
        /*source.sendSuccess(Component.literal("Reloaded config"), false);
        *///?}
        return 1;
    }

    private static int executeToggle(CommandContext<CommandSourceStack> ctx) {
        TT20.config.enabled(!TT20.config.enabled());
        TT20.config.save();

        return 1;
    }

    private static int executeTps(CommandContext<CommandSourceStack> ctx, boolean missedTicks) {
        CommandSourceStack source = ctx.getSource();
        //? if >=1.20.1 {
        source.sendSuccess(() -> Component.literal(
                "§7TPS " + TPSUtil.colorizeTPS(TT20.TPS_CALCULATOR.getTPS(), true) +
                        "§7 with average " + TPSUtil.colorizeTPS(TT20.TPS_CALCULATOR.getAverageTPS(), true) +
                        "§7 accurate " + TPSUtil.colorizeTPS(TT20.TPS_CALCULATOR.getMostAccurateTPS(), true)
        ), false);
        //?} else {
        /*source.sendSuccess(Component.literal(
                "§7TPS " + TPSUtil.colorizeTPS(TT20.TPS_CALCULATOR.getTPS(), true) +
                        "§7 with average " + TPSUtil.colorizeTPS(TT20.TPS_CALCULATOR.getAverageTPS(), true) +
                        "§7 accurate " + TPSUtil.colorizeTPS(TT20.TPS_CALCULATOR.getMostAccurateTPS(), true)
        ), false);
        *///?}


        if (missedTicks) {
            //? if >=1.20.1 {
            source.sendSuccess(() -> Component.literal(
                    "§8Missed ticks: §7" + TPSUtil.formatMissedTicks(TT20.TPS_CALCULATOR.getAllMissedTicks())
            ), false);
            //?} else {
            /*source.sendSuccess(Component.literal(
                    "§8Missed ticks: §7" + TPSUtil.formatMissedTicks(TT20.TPS_CALCULATOR.getAllMissedTicks())
            ), false);
            *///?}
        }

        return 1;
    }

    private static int executeStatus(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();
        //? if >=1.20.1 {
        source.sendSuccess(() -> Component.literal(
                "§7TT20 enabled: " + (TT20.config.enabled() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Block entity acceleration: " + (TT20.config.blockEntityAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Block breaking acceleration: " + (TT20.config.blockBreakingAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Potion effect acceleration: " + (TT20.config.potionEffectAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Fluid acceleration: " + (TT20.config.fluidAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Pickup acceleration: " + (TT20.config.pickupAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Eating acceleration: " + (TT20.config.eatingAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Portal acceleration: " + (TT20.config.portalAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Sleeping acceleration: " + (TT20.config.sleepingAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Time acceleration: " + (TT20.config.timeAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Random tickspeed acceleration: " + (TT20.config.randomTickSpeedAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Singleplayer warning: " + (TT20.config.singlePlayerWarning() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§7Server watchdog: " + (TT20.config.serverWatchdog() ? "§aON" : "§cOFF")
        ), false);
        //?} else {
        /*source.sendSuccess(Component.literal(
                "§7TT20 enabled: " + (TT20.config.enabled() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Block entity acceleration: " + (TT20.config.blockEntityAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Block breaking acceleration: " + (TT20.config.blockBreakingAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Potion effect acceleration: " + (TT20.config.potionEffectAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Fluid acceleration: " + (TT20.config.fluidAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Pickup acceleration: " + (TT20.config.pickupAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Eating acceleration: " + (TT20.config.eatingAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Portal acceleration: " + (TT20.config.portalAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Sleeping acceleration: " + (TT20.config.sleepingAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Time acceleration: " + (TT20.config.timeAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Random tickspeed acceleration: " + (TT20.config.randomTickSpeedAcceleration() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Singleplayer warning: " + (TT20.config.singlePlayerWarning() ? "§aON" : "§cOFF")
        ), false);
        source.sendSuccess(Component.literal(
                "§7Server watchdog: " + (TT20.config.serverWatchdog() ? "§aON" : "§cOFF")
        ), false);
        *///?}

        executeTps(ctx, false);

        //? if >=1.20.1 {
        source.sendSuccess(() -> Component.literal(
                "\n§8Version: §7" + TT20.VERSION
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§8MSPT: §7" + TT20.TPS_CALCULATOR.getMSPT()
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§8Missed ticks: §7" + TPSUtil.formatMissedTicks(TT20.TPS_CALCULATOR.getAllMissedTicks())
        ), false);
        source.sendSuccess(() -> Component.literal(
                "§8Automatic updater: §7" + (TT20.config.automaticUpdater() ? "§aenabled" : "§cdisabled")
        ), false);
        //?} else {
        /*source.sendSuccess(Component.literal(
                "\n§8Version: §7" + TT20.VERSION
        ), false);
        source.sendSuccess(Component.literal(
                "§8MSPT: §7" + TT20.TPS_CALCULATOR.getMSPT()
        ), false);
        source.sendSuccess(Component.literal(
                "§8Missed ticks: §7" + TPSUtil.formatMissedTicks(TT20.TPS_CALCULATOR.getAllMissedTicks())
        ), false);
        source.sendSuccess(Component.literal(
                "§8Automatic updater: §7" + (TT20.config.automaticUpdater() ? "§aenabled" : "§cdisabled")
        ), false);
        *///?}

        return 1;
    }
}
