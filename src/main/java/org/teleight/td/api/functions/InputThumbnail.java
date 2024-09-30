package org.teleight.td.api.functions;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.functions.file.InputFile;

public record InputThumbnail(
        InputFile thumbnail,
        int width,
        int height
) implements ApiObject {

    public @NotNull TdApi.InputThumbnail toTdObject() {
        return new TdApi.InputThumbnail(
                thumbnail.toTdObject(),
                width,
                height
        );
    }

}
