package org.teleight.td.bot.settings;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public record UserSettingsImpl(
        Path folder,
        PhoneNumber phoneNumber
) implements UserSettings {

    public static @NotNull UserSettings create(@NotNull Path sessionFolder, @NotNull PhoneNumber phoneNumber) {
        return new UserSettingsImpl(sessionFolder, phoneNumber);
    }

    @Override
    public @NotNull Path folder() {
        return folder;
    }

    @Override
    public @NotNull PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    static final class BuilderImpl implements Builder {
        private Path folder;
        private PhoneNumber phoneNumber;

        @Override
        public @NotNull Builder folder(@NotNull Path folder) {
            this.folder = folder;
            return this;
        }

        @Override
        public @NotNull Builder phoneNumber(@NotNull PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @Override
        public @NotNull UserSettings build() {
            return create(folder, phoneNumber);
        }
    }

}
