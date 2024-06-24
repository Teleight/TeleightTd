package org.teleight.td.commands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.td.api.objects.Chat;
import org.teleight.td.api.objects.message.sender.MessageSender;
import org.teleight.td.bot.TeleightClient;

@FunctionalInterface
public interface CommandHandler {

    void onCommand(@NotNull TeleightClient teleightClient, @NotNull Chat chat, @Nullable MessageSender sender, @NotNull String message);

}
