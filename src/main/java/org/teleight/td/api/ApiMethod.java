package org.teleight.td.api;

import it.tdlight.jni.TdApi;

public interface ApiMethod<F extends TdApi.Function<O>, O extends TdApi.Object, R extends ApiObject> {

    F wrapRequest();

    R wrapResponse(O internalResponse);

}
