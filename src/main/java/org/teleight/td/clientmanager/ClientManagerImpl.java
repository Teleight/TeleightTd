package org.teleight.td.clientmanager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.td.bot.TeleightClient;
import org.teleight.td.bot.TeleightClientImpl;
import org.teleight.td.bot.settings.TdSettings;

import java.util.function.Consumer;

public final class ClientManagerImpl implements ClientManager {

    private ClientProvider clientProvider = TeleightClientImpl::new;

    public ClientManagerImpl() {
    }

    @Override
    public void register(int apiId, @NotNull String apiHash, @NotNull TdSettings settings, @Nullable Consumer<TeleightClient> completeCallback) {
        final var teleightClientImpl = clientProvider.provide(apiId, apiHash, settings);
        teleightClientImpl.connect();

        if (completeCallback != null) {
            try {
                completeCallback.accept(teleightClientImpl);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public void close() {
        System.out.println("Closing BotManager");
    }

    @Override
    public @NotNull ClientProvider getClientProvider() {
        return clientProvider;
    }

    @Override
    public void setClientProvider(@NotNull ClientProvider clientProvider) {
        this.clientProvider = clientProvider;
    }

}
