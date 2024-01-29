package org.teleight.td;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.clientmanager.ClientManager;

public final class TeleightTd {

    private static TeleightTdProcess teleightTdProcess;

    public static @NotNull TeleightTd init() {
        updateProcess();
        return new TeleightTd();
    }

    @ApiStatus.Internal
    public static TeleightTdProcess updateProcess() {
        TeleightTdProcess process = new TeleightTdProcessImpl();
        teleightTdProcess = process;
        return process;
    }

    public static void stopCleanly() {
        teleightTdProcess.close();
    }

    public static @NotNull ClientManager getBotManager() {
        return teleightTdProcess.botManager();
    }

    public void start() {
        teleightTdProcess.start();
    }

}
