package org.teleight.td.api.objects.file;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;

public record RemoteFile(
        String id,
        String uniqueId,
        boolean isUploadingActive,
        boolean isUploadingCompleted,
        long uploadedSize
) implements ApiObject {

    public static @NotNull RemoteFile fromTdObject(@NotNull TdApi.RemoteFile remoteFile) {
        return new RemoteFile(
                remoteFile.id,
                remoteFile.uniqueId,
                remoteFile.isUploadingActive,
                remoteFile.isUploadingCompleted,
                remoteFile.uploadedSize
        );
    }

}
