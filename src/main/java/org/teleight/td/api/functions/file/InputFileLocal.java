package org.teleight.td.api.functions.file;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;

public record InputFileLocal(
        String path
) implements InputFile {

    @Override
    public @NotNull TdApi.InputFile toTdObject() {
        return new TdApi.InputFileLocal(path);
    }

}
