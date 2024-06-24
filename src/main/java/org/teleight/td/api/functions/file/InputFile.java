package org.teleight.td.api.functions.file;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;

public interface InputFile {

    @NotNull TdApi.InputFile toTdObject();

}
