package org.teleight.td;

import org.jetbrains.annotations.NotNull;
import org.teleight.td.clientmanager.ClientManager;

public sealed interface TeleightTdProcess permits TeleightTdProcessImpl {

    void start();

    void close();

    @NotNull ClientManager botManager();

}
