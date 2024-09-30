package org.teleight.td.api.objects.message.video;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.objects.message.FormattedText;
import org.teleight.td.api.objects.message.MessageContent;
import org.teleight.td.api.objects.video.Video;

public record MessageVideo(
        Video video,
        FormattedText caption,
        boolean hasSpoiler,
        boolean isSecret
) implements MessageContent {

    public static @NotNull MessageVideo fromTdObject(@NotNull TdApi.MessageVideo messageVideo) {
        return new MessageVideo(
                Video.fromTdObject(messageVideo.video),
                FormattedText.fromTdObject(messageVideo.caption),
                messageVideo.isSecret,
                messageVideo.isSecret
        );
    }

}
