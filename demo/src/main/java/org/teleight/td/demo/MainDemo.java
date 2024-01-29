package org.teleight.td.demo;

import it.tdlight.jni.TdApi;
import org.teleight.td.TeleightTd;
import org.teleight.td.api.functions.GetChat;
import org.teleight.td.api.objects.message.MessageText;
import org.teleight.td.api.updates.UpdateNewMessage;
import org.teleight.td.bot.settings.PhoneNumber;
import org.teleight.td.bot.settings.UserSettings;

public class MainDemo {

    public static void main(String[] args) {
        final TeleightTd teleight = TeleightTd.init();

        final String apiIdAsString = System.getenv("userbot.apiid") != null ? System.getenv("userbot.apiid") : "--INSERT-APIID--HERE";
        final String apiHash = System.getenv("userbot.apihash") != null ? System.getenv("userbot.apihash") : "--INSERT-APIHASH--HERE";
        final String phoneNumberWithPrefix = System.getenv("userbot.phonenumber") != null ? System.getenv("userbot.phonenumber") : "--INSERT-PHONE-NUMBER-WITH-PREFIX--HERE";
        //Example: +1-1234567890

        final int apiId;
        try {
            apiId = Integer.parseInt(apiIdAsString);
        } catch (NumberFormatException e) {
            System.err.println("Invalid API ID: " + apiIdAsString);
            System.exit(1);
            return;
        }
        var phoneNumber = PhoneNumber.fromPrefixAndNumber(phoneNumberWithPrefix);
        var settings = UserSettings.builder().folder("example-folder").phoneNumber(phoneNumber).build();
        TeleightTd.getBotManager().register(apiId, apiHash, settings, userBot -> {
            userBot.addUpdateHandler(UpdateNewMessage.class, update -> {
                var message = update.message();
                var messageContent = message.content();
                var chatId = message.chatId();

                String text;
                if (messageContent instanceof MessageText messageText) {
                    text = messageText.text().text();
                } else {
                    text = String.format("(%s)", messageContent.getClass().getSimpleName());
                }

                userBot.send(new GetChat(chatId))
                        .thenAcceptAsync(chat -> {
                            var title = chat.title();
                            System.out.printf("Received new message from chat %s (%s) [%s]: %s%n", title, chatId, message.id(), text);
                            System.out.println("================");
                        })
                        .exceptionally(throwable -> {
                            System.err.printf("Can't get chat title of chat %s%n", chatId);
                            throwable.printStackTrace(System.err);
                            return null;
                        });
            });



            userBot.UNSAFE_addUpdateHandler(TdApi.UpdateNewMessage.class, update -> {
                var message = update.message;
                var messageContent = message.content;
                var chatId = message.chatId;

                String text;
                if (messageContent instanceof TdApi.MessageText messageText) {
                    text = messageText.text.text;
                } else {
                    text = String.format("(%s)", messageContent.getClass().getSimpleName());
                }

                userBot.send(new GetChat(chatId))
                        .thenAcceptAsync(chat -> {
                            var title = chat.title();
                            System.out.printf("Received new message from chat %s (%s) [%s]: %s%n", title, chatId, message.id, text);
                            System.out.println("================");
                        })
                        .exceptionally(throwable -> {
                            System.err.printf("Can't get chat title of chat %s%n", chatId);
                            throwable.printStackTrace(System.err);
                            return null;
                        });
            });
        });

        teleight.start();
    }

}
