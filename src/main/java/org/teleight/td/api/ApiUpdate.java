package org.teleight.td.api;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;

public interface ApiUpdate<T extends TdApi.Update, R extends ApiUpdate.Result> {

    @NotNull Class<T> getOriginalUpdateType();

    @NotNull R wrapUpdate(@NotNull T internalResponse);


    interface Result{

    }

}
