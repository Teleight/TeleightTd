package org.teleight.td.api.objects.file;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;

public record LocalFile(
        String path,
        boolean canBeDownloaded,
        boolean canBeDeleted,
        boolean isDownloadingActive,
        boolean isDownloadingCompleted,
        long downloadOffset,
        long downloadedPrefixSize,
        long downloadedSize
) implements ApiObject {

    public static @NotNull LocalFile fromTdObject(@NotNull TdApi.LocalFile localFile) {
        return new LocalFile(
                localFile.path,
                localFile.canBeDownloaded,
                localFile.canBeDeleted,
                localFile.isDownloadingActive,
                localFile.isDownloadingCompleted,
                localFile.downloadOffset,
                localFile.downloadedPrefixSize,
                localFile.downloadedSize
        );
    }

}
