package org.teleight.td.bot;

import it.tdlight.client.GenericUpdateHandler;
import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.ApiUpdate;
import org.teleight.td.api.updates.UpdateHandler;

import java.util.concurrent.CompletableFuture;

public interface TeleightClient {

    <T extends TdApi.Update> void UNSAFE_addUpdateHandler(@NotNull Class<T> updateType, @NotNull GenericUpdateHandler<? super T> handler);

    <U extends ApiUpdate<T, R>, T extends TdApi.Update, R extends ApiUpdate.Result> void addUpdateHandler(@NotNull Class<U> updateType, @NotNull UpdateHandler<R> updateHandler);

    <R extends TdApi.Object> CompletableFuture<R> send(@NotNull TdApi.Function<R> function);

    <F extends TdApi.Function<O>, O extends TdApi.Object, R extends ApiObject> CompletableFuture<R> send(@NotNull ApiMethod<F, O, R> function);


    void connect();

    void close() throws Exception;

}
