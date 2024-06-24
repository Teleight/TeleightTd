package org.teleight.td.api.objects.message.photo;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.objects.message.FormattedText;
import org.teleight.td.api.objects.message.MessageContent;
import org.teleight.td.api.objects.photo.Photo;

public record MessagePhoto(
        Photo photo,
        FormattedText caption,
        boolean hasSpoiler,
        boolean isSecret
) implements MessageContent {

    public static @NotNull MessagePhoto fromTdObject(@NotNull TdApi.MessagePhoto messagePhoto) {
        return new MessagePhoto(
                Photo.fromTdObject(messagePhoto.photo),
                FormattedText.fromTdObject(messagePhoto.caption),
                messagePhoto.isSecret,
                messagePhoto.hasSpoiler
        );
    }

}
