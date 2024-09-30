package org.teleight.td.api.objects.video;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.Minithumbnail;
import org.teleight.td.api.objects.Thumbnail;
import org.teleight.td.api.objects.file.File;

public record Video(
        int duration,
        int width,
        int height,
        String fileName,
        String mimeType,
        boolean hasStickers,
        boolean supportsStreaming,
        Minithumbnail minithumbnail,
        Thumbnail thumbnail,
        File video
) implements ApiObject {

    public static @NotNull Video fromTdObject(@NotNull TdApi.Video video) {
        return new Video(
                video.duration,
                video.width,
                video.height,
                video.fileName,
                video.mimeType,
                video.hasStickers,
                video.supportsStreaming,
                Minithumbnail.fromTdObject(video.minithumbnail),
                Thumbnail.fromTdObject(video.thumbnail),
                File.fromTdObject(video.video)
        );
    }

}
