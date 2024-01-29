package org.teleight.td.clientmanager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.td.bot.TeleightClient;
import org.teleight.td.bot.settings.TdSettings;

import java.util.function.Consumer;

public sealed interface ClientManager permits ClientManagerImpl {

    default void register(int apiId, @NotNull String apiHash, @NotNull TdSettings settings) {
        register(apiId, apiHash, settings,null);
    }

    void register(int apiId, @NotNull String apiHash, @NotNull TdSettings settings, @Nullable Consumer<TeleightClient> completeCallback);

    @NotNull ClientProvider getClientProvider();

    void setClientProvider(@NotNull ClientProvider clientProvider);

}
