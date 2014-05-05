/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flamefeed.server;

import flamefeed.server.log.LogCommand;
import flamefeed.server.log.LogEventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import flamefeed.server.ServerConfigHandler;
import flamefeed.server.log.EventLogger;
import flamefeed.server.protect.ProtectCommand;
import flamefeed.server.protect.ProtectEventHandler;
import java.util.logging.Level;
import net.minecraftforge.common.MinecraftForge;

/**
 *
 * @author Anedaar
 */

public class ServerProxy {
    
    
    public void preInit(FMLPreInitializationEvent event) {
        ServerConfigHandler.init(event.getSuggestedConfigurationFile());
        EventLogger.init();
    }

    public void postInit(FMLPostInitializationEvent event) {    
        EventLogger.log(Level.INFO, "registering EventHandlers");
        MinecraftForge.EVENT_BUS.register(new ProtectEventHandler());
        MinecraftForge.EVENT_BUS.register(new LogEventHandler());
    }

    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new ProtectCommand());
        event.registerServerCommand(new LogCommand());
        event.registerServerCommand(new WhatCommand());
    }

}
