package org.teleight.td.api.functions;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.api.functions.messagecontent.InputMessageContent;
import org.teleight.td.api.objects.Message;

public class SendMessage implements ApiMethod<TdApi.SendMessage, TdApi.Message, Message> {

    private final long chatId;
    private final long messageThreadId;
    private final InputMessageContent inputMessageContent;

    public SendMessage(long chatId, long messageThreadId, InputMessageContent inputMessageContent) {
        this.chatId = chatId;
        this.messageThreadId = messageThreadId;
        this.inputMessageContent = inputMessageContent;
    }

    @Override
    public @NotNull TdApi.SendMessage wrapRequest() {
        return new TdApi.SendMessage(chatId, messageThreadId, null, null, null, inputMessageContent.toTdObject());
    }

    @Override
    public @NotNull Message wrapResponse(TdApi.@NotNull Message internalResponse) {
        return Message.fromTdObject(internalResponse);
    }

}
