package org.teleight.td.api.objects;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.file.File;

public record Thumbnail(
        int width,
        int height,
        File file
) implements ApiObject {

    public static @NotNull Thumbnail fromTdObject(@NotNull TdApi.Thumbnail thumbnail) {
        return new Thumbnail(
                thumbnail.width,
                thumbnail.height,
                File.fromTdObject(thumbnail.file)
        );
    }

}
