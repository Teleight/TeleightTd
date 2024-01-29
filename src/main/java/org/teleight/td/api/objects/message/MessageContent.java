package org.teleight.td.api.objects.message;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.td.api.ApiObject;

public interface MessageContent extends ApiObject {

    static @Nullable MessageContent fromTdObject(@NotNull TdApi.MessageContent content) {
        if (content instanceof TdApi.MessageText messageText) {
            return MessageText.fromTdObject(messageText);
        }
        return null;
    }

}
