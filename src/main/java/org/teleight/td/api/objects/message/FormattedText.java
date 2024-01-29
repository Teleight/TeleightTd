package org.teleight.td.api.objects.message;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;

public record FormattedText(
        String text
) implements ApiObject {

    public static @NotNull FormattedText fromTdObject(@NotNull TdApi.FormattedText formattedText) {
        return new FormattedText(formattedText.text);
    }

}
