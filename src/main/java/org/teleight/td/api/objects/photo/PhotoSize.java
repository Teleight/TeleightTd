package org.teleight.td.api.objects.photo;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.file.File;

public record PhotoSize(
        String type,
        File photo,
        int width,
        int height,
        int[] progressiveSizes
) implements ApiObject {

    public static @NotNull PhotoSize fromTdObject(@NotNull TdApi.PhotoSize photoSize) {
        return new PhotoSize(
                photoSize.type,
                File.fromTdObject(photoSize.photo),
                photoSize.width,
                photoSize.height,
                photoSize.progressiveSizes
        );
    }

}
