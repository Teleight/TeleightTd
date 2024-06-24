package org.teleight.td.api.functions.messagecontent;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;

public interface InputMessageContent extends ApiObject {

    @NotNull TdApi.InputMessageContent toTdObject();

}
