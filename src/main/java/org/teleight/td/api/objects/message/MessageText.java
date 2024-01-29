package org.teleight.td.api.objects.message;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;

public record MessageText(
        FormattedText text
) implements MessageContent {

    public static @NotNull MessageText fromTdObject(@NotNull TdApi.MessageText messageText){
        return new MessageText(
                FormattedText.fromTdObject(messageText.text)
        );
    }

}
