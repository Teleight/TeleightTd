package org.teleight.td.api.objects.message.sender;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.message.MessageContent;

public record MessageSenderUser(
        long userId
) implements MessageSender {

    public static @NotNull MessageSenderUser fromTdObject(@NotNull TdApi.MessageSenderUser messageSenderUser) {
        return new MessageSenderUser(messageSenderUser.userId);
    }

}
