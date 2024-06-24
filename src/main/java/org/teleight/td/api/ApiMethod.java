package org.teleight.td.api;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;

public interface ApiMethod<F extends TdApi.Function<O>, O extends TdApi.Object, R extends ApiObject> {

    @NotNull F wrapRequest();

    @NotNull R wrapResponse(@NotNull O internalResponse);

}
