package org.teleight.td.api.functions.messagecontent;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.functions.file.InputFile;
import org.teleight.td.api.functions.InputThumbnail;
import org.teleight.td.api.objects.message.FormattedText;

public record InputMessageDocument(
        InputFile document,
        InputThumbnail thumbnail,
        boolean disableContentTypeDetection,
        FormattedText caption
) implements InputMessageContent{

    @Override
    public @NotNull TdApi.InputMessageContent toTdObject() {
        TdApi.InputThumbnail tdThumbnail = null;
        if(thumbnail != null) {
            tdThumbnail = thumbnail.toTdObject();
        }
        return new TdApi.InputMessageDocument(
                document.toTdObject(),
                tdThumbnail,
                disableContentTypeDetection,
                caption.toTdObject()
        );
    }

}
