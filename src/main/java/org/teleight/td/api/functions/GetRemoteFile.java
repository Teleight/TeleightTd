package org.teleight.td.api.functions;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.api.objects.file.File;
import org.teleight.td.api.objects.file.types.FileType;

public class GetRemoteFile<T extends TdApi.FileType> implements ApiMethod<TdApi.GetRemoteFile, TdApi.File, File> {

    private final String remoteFileId;
    private final FileType<T> fileType;

    public GetRemoteFile(String remoteFileId, FileType<T> fileType) {
        this.remoteFileId = remoteFileId;
        this.fileType = fileType;
    }

    @Override
    public TdApi.@NotNull GetRemoteFile wrapRequest() {
        return new TdApi.GetRemoteFile(remoteFileId, fileType.wrap());
    }

    @Override
    public @NotNull File wrapResponse(TdApi.@NotNull File internalResponse) {
        return File.fromTdObject(internalResponse);
    }

}
