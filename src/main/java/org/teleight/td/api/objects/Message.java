package org.teleight.td.api.objects;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.message.MessageContent;
import org.teleight.td.api.objects.message.sender.MessageSender;

public record Message(
        long id,
        MessageSender senderId,
        long chatId,
        MessageContent content,
        int date
) implements ApiObject {

    public static @NotNull Message fromTdObject(@NotNull TdApi.Message message) {
        return new Message(
                message.id,
                MessageSender.fromTdObject(message.senderId),
                message.chatId,
                MessageContent.fromTdObject(message.content),
                message.date
        );
    }

}
