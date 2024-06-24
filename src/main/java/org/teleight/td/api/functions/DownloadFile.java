package org.teleight.td.api.functions;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.api.objects.file.File;

public class DownloadFile implements ApiMethod<TdApi.DownloadFile, TdApi.File, File> {

    private final int fileId;
    private final int priority;
    private final long offset;
    private final long limit;
    private final boolean synchronous;

    public DownloadFile(int fileId, int priority, long offset, long limit, boolean synchronous) {
        this.fileId = fileId;
        this.priority = priority;
        this.offset = offset;
        this.limit = limit;
        this.synchronous = synchronous;
    }

    @Override
    public @NotNull TdApi.DownloadFile wrapRequest() {
        return new TdApi.DownloadFile(fileId, priority, offset, limit, synchronous);
    }

    @Override
    public @NotNull File wrapResponse(TdApi.@NotNull File internalResponse) {
        return File.fromTdObject(internalResponse);
    }

}
