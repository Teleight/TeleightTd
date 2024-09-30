package org.teleight.td.api.objects.message;

import it.tdlight.jni.TdApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.objects.message.audio.MessageAudio;
import org.teleight.td.api.objects.message.photo.MessagePhoto;
import org.teleight.td.api.objects.message.video.MessageVideo;

public interface MessageContent extends ApiObject {

    static @Nullable MessageContent fromTdObject(@NotNull TdApi.MessageContent content) {
        return switch (content) {
            case TdApi.MessageText messageText -> MessageText.fromTdObject(messageText);
            case TdApi.MessagePhoto messagePhoto -> MessagePhoto.fromTdObject(messagePhoto);
            case TdApi.MessageAudio messageAudio -> MessageAudio.fromTdObject(messageAudio);
            case TdApi.MessageVideo messageVideo -> MessageVideo.fromTdObject(messageVideo);
            default -> null;
        };
    }

}
