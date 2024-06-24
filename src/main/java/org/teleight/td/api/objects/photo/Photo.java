package org.teleight.td.api.objects.photo;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.Minithumbnail;

public record Photo(
        boolean hasStickers,
        Minithumbnail minithumbnail,
        PhotoSize[] sizes
) implements ApiObject {

    public static @NotNull Photo fromTdObject(@NotNull TdApi.Photo photo) {
        final var tdPhotoSizes = photo.sizes;
        final PhotoSize[] photoSizes = new PhotoSize[tdPhotoSizes.length];
        for (int i = 0; i < tdPhotoSizes.length; i++) {
            photoSizes[i] = PhotoSize.fromTdObject(tdPhotoSizes[i]);
        }
        return new Photo(
                photo.hasStickers,
                Minithumbnail.fromTdObject(photo.minithumbnail),
                photoSizes
        );
    }

}
