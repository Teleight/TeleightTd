package org.teleight.td.clientmanager;

import org.jetbrains.annotations.NotNull;
import org.teleight.td.bot.TeleightClient;
import org.teleight.td.bot.settings.TdSettings;

@FunctionalInterface
public interface ClientProvider {

    @NotNull TeleightClient provide(int apiId, @NotNull String apiHash, @NotNull TdSettings settings);

}
