package org.teleight.td.api.objects.message.sender;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.td.api.ApiObject;

public interface MessageSender extends ApiObject {

    static @Nullable MessageSender fromTdObject(@NotNull TdApi.MessageSender content) {
        if (content instanceof TdApi.MessageSenderUser messageSenderUser) {
            return MessageSenderUser.fromTdObject(messageSenderUser);
        }
        if (content instanceof TdApi.MessageSenderChat messageSenderChat) {
            return MessageSenderChat.fromTdObject(messageSenderChat);
        }
        return null;
    }

}
