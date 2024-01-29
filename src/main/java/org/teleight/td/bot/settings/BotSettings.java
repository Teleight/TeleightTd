package org.teleight.td.bot.settings;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface BotSettings extends TdSettings {

    static @NotNull Builder builder() {
        return new BotSettingsImpl.BuilderImpl();
    }

    @NotNull String token();

    sealed interface Builder permits BotSettingsImpl.BuilderImpl {
        default @NotNull Builder folder(@NotNull String folder) {
            return folder(Path.of(folder));
        }

        @NotNull Builder folder(@NotNull Path folder);

        @NotNull Builder token(@NotNull String token);

        @NotNull BotSettings build();
    }

}
