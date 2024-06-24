package org.teleight.td.api.objects.message;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.message.photo.MessagePhoto;

public interface MessageContent extends ApiObject {

    static @Nullable MessageContent fromTdObject(@NotNull TdApi.MessageContent content) {
        if (content instanceof TdApi.MessageText messageText) {
            return MessageText.fromTdObject(messageText);
        }
        if (content instanceof TdApi.MessagePhoto messagePhoto) {
            return MessagePhoto.fromTdObject(messagePhoto);
        }
        return null;
    }

}
