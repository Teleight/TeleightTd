package org.teleight.td.api.objects.message.sender;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;

public record MessageSenderChat(
        long chatId
) implements MessageSender {

    public static @NotNull MessageSenderChat fromTdObject(@NotNull TdApi.MessageSenderChat messageSenderChat) {
        return new MessageSenderChat(messageSenderChat.chatId);
    }

}
