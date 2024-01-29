package org.teleight.td.bot.settings;

import org.jetbrains.annotations.NotNull;

public sealed interface PhoneNumber permits PhoneNumber.PhoneNumberImpl {

    static @NotNull PhoneNumber of(@NotNull String prefix, @NotNull String number) {
        return PhoneNumberImpl.create(prefix, number);
    }

    static @NotNull Builder builder() {
        return PhoneNumberImpl.builder();
    }

    static @NotNull PhoneNumber fromPrefixAndNumber(String fullPhoneNumber){
        if(!fullPhoneNumber.startsWith("+")) {
            throw new IllegalArgumentException("Invalid phone number format. Expected: +1-1234567890");
        }
        final String[] split = fullPhoneNumber.split("-");
        if(split.length != 2) {
            throw new IllegalArgumentException("Invalid phone number format. Expected: +1-1234567890");
        }
        final String prefix = split[0];
        final String number = split[1];
        return PhoneNumber.of(prefix, number);
    }


    @NotNull String prefix();

    @NotNull String number();

    default @NotNull String getAsString(){
        return prefix() + number();
    }


    interface Builder {
        @NotNull Builder prefix(@NotNull String prefix);

        @NotNull Builder number(@NotNull String number);

        @NotNull PhoneNumber build();
    }

    record PhoneNumberImpl(
            @NotNull String prefix,
            @NotNull String number
    ) implements PhoneNumber {

        static @NotNull PhoneNumber create(@NotNull String prefix, @NotNull String number) {
            return new PhoneNumberImpl(prefix, number);
        }

        static @NotNull Builder builder() {
            return new BuilderImpl();
        }

        private static class BuilderImpl implements Builder {
            private String prefix;
            private String number;

            @Override
            public @NotNull Builder prefix(@NotNull String prefix) {
                this.prefix = prefix;
                return this;
            }

            @Override
            public @NotNull Builder number(@NotNull String number) {
                this.number = number;
                return this;
            }

            @Override
            public @NotNull PhoneNumber build() {
                return create(prefix, number);
            }
        }
    }

}
