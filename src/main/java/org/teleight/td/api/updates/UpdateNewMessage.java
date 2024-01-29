package org.teleight.td.api.updates;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiUpdate;
import org.teleight.td.api.objects.Message;

public class UpdateNewMessage implements ApiUpdate<TdApi.UpdateNewMessage, UpdateNewMessage.Result> {

    @Override
    public @NotNull Class<TdApi.UpdateNewMessage> getOriginalUpdateType() {
        return TdApi.UpdateNewMessage.class;
    }

    @Override
    public @NotNull Result wrapUpdate(TdApi.@NotNull UpdateNewMessage internalResponse) {
        return new Result(Message.fromTdObject(internalResponse.message));
    }


    public record Result(
            Message message
    ) implements ApiUpdate.Result {

    }

}
