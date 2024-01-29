package org.teleight.td.api.objects;

import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.message.MessageContent;

public record Message(
        long id,

        long chatId,

        MessageContent content
) implements ApiObject {

    public static @NotNull Message fromTdObject(@NotNull it.tdlight.jni.TdApi.Message message) {
        return new Message(
                message.id,
                message.chatId,
                MessageContent.fromTdObject(message.content)
        );
    }

}
