package org.teleight.td.api.objects.message.document;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.objects.document.Document;
import org.teleight.td.api.objects.message.FormattedText;
import org.teleight.td.api.objects.message.MessageContent;

public record MessageDocument(
        Document document,
        FormattedText caption
) implements MessageContent {

    public static @NotNull MessageDocument fromTdObject(@NotNull TdApi.MessageDocument messageDocument) {
        return new MessageDocument(
                Document.fromTdObject(messageDocument.document),
                FormattedText.fromTdObject(messageDocument.caption)
        );
    }

}
