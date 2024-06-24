package org.teleight.td.api.objects.audio;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.Minithumbnail;
import org.teleight.td.api.objects.Thumbnail;
import org.teleight.td.api.objects.file.File;

public record Audio(
        int duration,
        String title,
        String performer,
        String fileName,
        String mimeType,
        Minithumbnail albumCoverMinithumbnail,
        Thumbnail albumCoverThumbnail,
        Thumbnail[] externalAlbumCovers,
        File audio
) implements ApiObject {

    public static @NotNull Audio fromTdObject(@NotNull TdApi.Audio audio) {
        final var tdExternalAlbumCovers = audio.externalAlbumCovers;
        final var externalAlbumCovers = new Thumbnail[tdExternalAlbumCovers.length];
        for (int i = 0; i < tdExternalAlbumCovers.length; i++) {
            externalAlbumCovers[i] = Thumbnail.fromTdObject(tdExternalAlbumCovers[i]);
        }
        return new Audio(
                audio.duration,
                audio.title,
                audio.performer,
                audio.fileName,
                audio.mimeType,
                Minithumbnail.fromTdObject(audio.albumCoverMinithumbnail),
                Thumbnail.fromTdObject(audio.albumCoverThumbnail),
                externalAlbumCovers,
                File.fromTdObject(audio.audio)
        );
    }

}
