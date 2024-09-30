package org.teleight.td.api.functions.messagecontent;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.functions.file.InputFile;
import org.teleight.td.api.objects.message.FormattedText;

public record InputMessagePhoto(
        InputFile photo,
        int[] addedStickerFileIds,
        int width,
        int height,
        FormattedText caption,
        boolean hasSpoiler
) implements InputMessageContent {

    @Override
    public TdApi.@NotNull InputMessageContent toTdObject() {
        return new TdApi.InputMessagePhoto(photo.toTdObject(), null, addedStickerFileIds, width, height, caption.toTdObject(), null, hasSpoiler);
    }

}
