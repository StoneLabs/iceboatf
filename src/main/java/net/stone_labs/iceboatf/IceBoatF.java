package net.stone_labs.iceboatf;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IceBoatF implements DedicatedServerModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "iceboatf";
    public static final String MOD_NAME = "IceBoatF";
    public static final String VERSION = "1.0.0";

    public static boolean disableIceBoats = false;

    @Override
    public void onInitializeServer()
    {
        LOGGER.log(Level.INFO, "Initialized {} version {}", MOD_NAME, VERSION);

        // Set values from gamerules on server start
        ServerLifecycleEvents.SERVER_STARTED.register(server ->
        {
            disableIceBoats = server.getGameRules().get(DISABLE_ICE_BOATS).get();
        });
    }

    public static final GameRules.Key<GameRules.BooleanRule> DISABLE_ICE_BOATS = register("disableIceBoats", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true, (server, rule) ->
    {
        disableIceBoats = rule.get();
    }));

    private static <T extends GameRules.Rule<T>> GameRules.Key<T> register(String name, GameRules.Category category, GameRules.Type<T> type)
    {
        return GameRuleRegistry.register(name, category, type);
    }
}
