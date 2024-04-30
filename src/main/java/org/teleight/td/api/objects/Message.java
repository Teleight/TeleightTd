package org.teleight.td.api.objects;

import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.message.MessageContent;
import org.teleight.td.api.objects.message.sender.MessageSender;

public record Message(
        long id,
        MessageSender senderId,
        long chatId,
        MessageContent content
) implements ApiObject {

    public static @NotNull Message fromTdObject(@NotNull it.tdlight.jni.TdApi.Message message) {
        return new Message(
                message.id,
                MessageSender.fromTdObject(message.senderId),
                message.chatId,
                MessageContent.fromTdObject(message.content)
        );
    }

}
