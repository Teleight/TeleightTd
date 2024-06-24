package org.teleight.td.api.objects.file.types;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;

public record FileTypePhoto(

) implements FileType<TdApi.FileTypePhoto> {


    @Override
    public @NotNull TdApi.FileTypePhoto wrap() {
        return new TdApi.FileTypePhoto();
    }

}
