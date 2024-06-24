package org.teleight.td.api.objects;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiObject;

public record Chat(
        long id,
        String title
) implements ApiObject {

    public static @NotNull Chat fromTdObject(TdApi.Chat chat){
        return new Chat(
                chat.id,
                chat.title
        );
    }

}
