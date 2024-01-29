package org.teleight.td.api.functions;

import it.tdlight.jni.TdApi;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.api.objects.Chat;

public class GetChat implements ApiMethod<TdApi.GetChat, TdApi.Chat, Chat> {

    private final long chatId;

    public GetChat(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public TdApi.GetChat wrapRequest() {
        return new TdApi.GetChat(chatId);
    }

    @Override
    public Chat wrapResponse(TdApi.Chat internalResponse) {
        return new Chat(internalResponse.title);
    }

}
