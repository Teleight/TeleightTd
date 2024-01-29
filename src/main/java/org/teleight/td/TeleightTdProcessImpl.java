package org.teleight.td;

import org.jetbrains.annotations.NotNull;
import org.teleight.td.clientmanager.ClientManager;
import org.teleight.td.clientmanager.ClientManagerImpl;

final class TeleightTdProcessImpl implements TeleightTdProcess {

    private final ClientManagerImpl botManager;

    public TeleightTdProcessImpl() {
        botManager = new ClientManagerImpl();
    }

    @Override
    public void start() {
        System.out.println("Started Teleight");

        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        System.out.println("Shutting down Teleight");

        botManager.close();
    }

    @Override
    public @NotNull ClientManager botManager() {
        return botManager;
    }

}
