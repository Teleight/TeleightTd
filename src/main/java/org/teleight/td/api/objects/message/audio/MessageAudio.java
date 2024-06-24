package org.teleight.td.api.objects.message.audio;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.objects.audio.Audio;
import org.teleight.td.api.objects.message.FormattedText;
import org.teleight.td.api.objects.message.MessageContent;

public record MessageAudio(
        Audio audio,
        FormattedText formattedText
) implements MessageContent {

    public static @NotNull MessageAudio fromTdObject(@NotNull TdApi.MessageAudio messageAudio) {
        return new MessageAudio(
                Audio.fromTdObject(messageAudio.audio),
                FormattedText.fromTdObject(messageAudio.caption)
        );
    }

}
