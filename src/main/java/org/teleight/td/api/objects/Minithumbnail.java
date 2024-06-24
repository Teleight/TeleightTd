package org.teleight.td.api.objects;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;

public record Minithumbnail(
        int width,
        int height,
        byte[] data
) implements ApiObject {

    public static @NotNull Minithumbnail fromTdObject(@NotNull TdApi.Minithumbnail minithumbnail) {
        return new Minithumbnail(
                minithumbnail.width,
                minithumbnail.height,
                minithumbnail.data
        );
    }

}
