package org.teleight.td.api.objects.file;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;

public record File(
        int id,
        long size,
        long expectedSize,
        LocalFile local,
        RemoteFile remote
) implements ApiObject {

    public static @NotNull File fromTdObject(@NotNull TdApi.File photo) {
        return new File(
                photo.id,
                photo.size,
                photo.expectedSize,
                LocalFile.fromTdObject(photo.local),
                RemoteFile.fromTdObject(photo.remote)
        );
    }

}
