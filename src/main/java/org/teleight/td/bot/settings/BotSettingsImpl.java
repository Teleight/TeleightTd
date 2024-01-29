package org.teleight.td.bot.settings;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public record BotSettingsImpl(
        Path folder,
        String token
) implements BotSettings {

    public static @NotNull BotSettings create(@NotNull Path sessionFolder, @NotNull String token) {
        return new BotSettingsImpl(sessionFolder, token);
    }

    static final class BuilderImpl implements Builder {
        private Path folder;
        private String token;

        @Override
        public @NotNull Builder folder(@NotNull Path folder) {
            this.folder = folder;
            return this;
        }

        @Override
        public @NotNull Builder token(@NotNull String token) {
            this.token = token;
            return this;
        }

        @Override
        public @NotNull BotSettings build() {
            return create(folder, token);
        }
    }

}
