package org.teleight.td.api.objects.file.types;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;

@FunctionalInterface
public interface FileType<T extends TdApi.FileType> extends ApiObject {

    @NotNull T wrap();

}
