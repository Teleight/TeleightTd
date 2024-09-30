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
        return new Document(
                document.fileName,
                document.mimeType,
                Minithumbnail.fromTdObject(document.minithumbnail),
                Thumbnail.fromTdObject(document.thumbnail),
                File.fromTdObject(document.document)
        );
    }

}
