package org.teleight.td.bot;

import it.tdlight.client.GenericResultHandler;
import it.tdlight.client.GenericUpdateHandler;
import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.ApiUpdate;
import org.teleight.td.api.updates.UpdateHandler;
import org.teleight.td.commands.CommandHandler;

import java.util.concurrent.CompletableFuture;

public interface TeleightClient {

    /*
    UPDATES
    */

    <U extends ApiUpdate<T, R>, T extends TdApi.Update, R extends ApiUpdate.Result> void addUpdateHandler(@NotNull Class<U> updateType, @NotNull UpdateHandler<R> updateHandler);

    <T extends TdApi.Update> void UNSAFE_addUpdateHandler(@NotNull Class<T> updateType, @NotNull GenericUpdateHandler<? super T> handler);

    /*
    COMMANDS
    */

    void addCommandHandler(@NotNull String commandName, @NotNull CommandHandler updateHandler);

    void UNSAFE_addCommandHandler(@NotNull String commandName, @NotNull it.tdlight.client.CommandHandler updateHandler);

    /*
    SEND
    */

    <R extends TdApi.Object> void send(@NotNull TdApi.Function<R> function, GenericResultHandler<R> resultHandler);

    <R extends TdApi.Object> CompletableFuture<R> send(@NotNull TdApi.Function<R> function);

    <F extends TdApi.Function<O>, O extends TdApi.Object, R extends ApiObject> CompletableFuture<R> send(@NotNull ApiMethod<F, O, R> function);


    void connect();

    void close() throws Exception;

}
