package org.teleight.td.bot;

import it.tdlight.client.GenericUpdateHandler;
import it.tdlight.client.SimpleTelegramClient;
import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiUpdate;
import org.teleight.td.api.updates.UpdateHandler;
import org.teleight.td.api.updates.UpdateNewMessage;

import java.util.HashMap;
import java.util.Map;

class TdUpdateHandler {

    private final SimpleTelegramClient internalClient;

    private final Map<Class<? extends ApiUpdate<?, ?>>, ApiUpdate<?, ?>> wrappedHandlers = new HashMap<>();

    public TdUpdateHandler(SimpleTelegramClient internalClient) {
        this.internalClient = internalClient;

        UNSAFE_addUpdateHandler(TdApi.UpdateAuthorizationState.class, this::handleAuthorization);

        wrappedHandlers.put(UpdateNewMessage.class, new UpdateNewMessage());
    }

    public <T extends TdApi.Update> void UNSAFE_addUpdateHandler(@NotNull Class<T> updateType, @NotNull GenericUpdateHandler<? super T> handler) {
        internalClient.addUpdateHandler(updateType, handler);
    }

    public <T extends ApiUpdate<U, R>, U extends TdApi.Update, R extends ApiUpdate.Result> void addUpdateHandler(@NotNull Class<T> updateType, @NotNull UpdateHandler<R> updateHandler) {
        //noinspection unchecked
        ApiUpdate<U, R> wrappedHandler = (ApiUpdate<U, R>) this.wrappedHandlers.get(updateType);
        if (wrappedHandler == null) {
            throw new IllegalArgumentException("No update handler for " + updateType);
        }
        var originalUpdateType = wrappedHandler.getOriginalUpdateType();
        UNSAFE_addUpdateHandler(originalUpdateType, originalResponse -> {
            var wrappedUpdate = wrappedHandler.wrapUpdate(originalResponse);
            updateHandler.handle(wrappedUpdate);
        });
    }


    private void handleAuthorization(@NotNull TdApi.UpdateAuthorizationState update) {
        final TdApi.AuthorizationState authorizationState = update.authorizationState;
        if (authorizationState instanceof TdApi.AuthorizationStateWaitCode) {
            System.out.println("Enter code");
        }
        if (authorizationState instanceof TdApi.AuthorizationStateWaitPassword) {
            System.out.println("Enter password");
        }

        if (authorizationState instanceof TdApi.AuthorizationStateReady) {
            System.out.println("Logged in");
        }

        if (authorizationState instanceof TdApi.AuthorizationStateLoggingOut) {
            System.out.println("The connection has been terminated. Maybe the session has been killed from privacy settings");
        }

        if (authorizationState instanceof TdApi.AuthorizationStateClosed) {
            System.out.println("Td client closed");
        }
    }

}
