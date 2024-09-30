package org.teleight.td.api.objects.document;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.Minithumbnail;
import org.teleight.td.api.objects.Thumbnail;
import org.teleight.td.api.objects.file.File;

public record Document(
        String fileName,
        String mimeType,
        Minithumbnail minithumbnail,
        Thumbnail thumbnail,
        File document
) implements ApiObject {

    public static @NotNull Document fromTdObject(@NotNull TdApi.Document document) {
        final var tdMinithumbnail = document.minithumbnail;
        Minithumbnail minithumbnail = null;
        if(tdMinithumbnail != null){
            minithumbnail = Minithumbnail.fromTdObject(tdMinithumbnail);
        }

        final var tdThumbnail = document.thumbnail;
        Thumbnail thumbnail = null;
        if(tdThumbnail != null) {
            thumbnail = Thumbnail.fromTdObject(tdThumbnail);
        }
        return new Document(
                document.fileName,
                document.mimeType,
                minithumbnail,
                thumbnail,
                File.fromTdObject(document.document)
        );
    }

}
