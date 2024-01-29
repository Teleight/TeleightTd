package org.teleight.td.bot.settings;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface UserSettings extends TdSettings {

    static @NotNull Builder builder() {
        return new UserSettingsImpl.BuilderImpl();
    }

    @NotNull PhoneNumber phoneNumber();

    sealed interface Builder permits UserSettingsImpl.BuilderImpl {
        default @NotNull Builder folder(@NotNull String folder) {
            return folder(Path.of(folder));
        }

        @NotNull Builder folder(@NotNull Path folder);

        @NotNull Builder phoneNumber(@NotNull PhoneNumber phoneNumber);

        @NotNull UserSettings build();
    }

}
