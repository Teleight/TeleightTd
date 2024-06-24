package org.teleight.td.bot;

import it.tdlight.Init;
import it.tdlight.Log;
import it.tdlight.Slf4JLogMessageHandler;
import it.tdlight.client.*;
import it.tdlight.jni.TdApi;
import it.tdlight.util.UnsupportedNativeLibraryException;
import org.jetbrains.annotations.NotNull;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.api.ApiObject;
import org.teleight.td.api.ApiUpdate;
import org.teleight.td.api.updates.UpdateHandler;
import org.teleight.td.bot.settings.BotSettings;
import org.teleight.td.bot.settings.TdSettings;
import org.teleight.td.bot.settings.UserSettings;
import org.teleight.td.commands.CommandHandler;

import java.util.concurrent.CompletableFuture;

public class TeleightClientImpl implements TeleightClient {

    //Settings
    private final int apiId;
    private final String apiHash;
    private final TdSettings settings;

    //Auth
    private final Object AUTH_LOCK = new Object();


    private SimpleTelegramClient client;
    private TdUpdateHandler tdUpdateHandler;

    public TeleightClientImpl(int apiId, @NotNull String apiHash, @NotNull TdSettings settings) {
        this.apiId = apiId;
        this.apiHash = apiHash;
        this.settings = settings;
    }

    @Override
    public void close() throws Exception {
        client.close();
        Log.disable();
    }

    /*
    UPDATES
    */

    @Override
    public <U extends ApiUpdate<T, R>, T extends TdApi.Update, R extends ApiUpdate.Result> void addUpdateHandler(@NotNull Class<U> updateType, @NotNull UpdateHandler<R> updateHandler) {
        tdUpdateHandler.addUpdateHandler(updateType, updateHandler);
    }

    @Override
    public <T extends TdApi.Update> void UNSAFE_addUpdateHandler(@NotNull Class<T> updateType, @NotNull GenericUpdateHandler<? super T> handler) {
        tdUpdateHandler.UNSAFE_addUpdateHandler(updateType, handler);
    }

    /*
    COMMANDS
    */

    @Override
    public void addCommandHandler(@NotNull String commandName, @NotNull CommandHandler updateHandler) {
        tdUpdateHandler.addCommandHandler(commandName, updateHandler);
    }

    @Override
    public void UNSAFE_addCommandHandler(@NotNull String commandName, @NotNull it.tdlight.client.CommandHandler updateHandler) {
        tdUpdateHandler.UNSAFE_addCommandHandler(commandName, updateHandler);
    }


    /*
    SEND
    */

    @Override
    public <R extends TdApi.Object> void send(TdApi.@NotNull Function<R> function, GenericResultHandler<R> resultHandler) {
        client.send(function, resultHandler);
    }

    @Override
    public <R extends TdApi.Object> CompletableFuture<R> send(@NotNull TdApi.Function<R> function) {
        return client.send(function);
    }

    @Override
    public <F extends TdApi.Function<O>, O extends TdApi.Object, R extends ApiObject> CompletableFuture<R> send(@NotNull ApiMethod<F, O, R> function) {
        CompletableFuture<R> result = new CompletableFuture<>();

        var wrappedRequest = function.wrapRequest();

        CompletableFuture<O> internalRequest = client.send(wrappedRequest);
        internalRequest
                .thenAcceptAsync(internalResponse -> {
                    var wrappedResponse = function.wrapResponse(internalResponse);
                    result.complete(wrappedResponse);
                })
                .exceptionally(throwable -> {
                    result.completeExceptionally(throwable);
                    return null;
                });
        return result;
    }



    @Override
    public void connect() {
        var thread = new Thread(() -> {
            try {
                Init.init();
            } catch (UnsupportedNativeLibraryException e) {
                throw new RuntimeException(e);
            }
            Log.setLogMessageHandler(1, new Slf4JLogMessageHandler());

            try (var clientFactory = new SimpleTelegramClientFactory();) {
                var apiToken = new APIToken(apiId, apiHash);

                //This is what Telegram sends once authentication is complete
                //App short name, {application version}, TDLib version, {device model}, system version (like: android), {system version}

                final var sessionFolder = settings.folder();
                var tdLibSettings = TDLibSettings.create(apiToken);
                tdLibSettings.setUseTestDatacenter(false);
                tdLibSettings.setDatabaseDirectoryPath(sessionFolder.resolve("data"));
                tdLibSettings.setDownloadedFilesDirectoryPath(sessionFolder.resolve("downloads"));
                tdLibSettings.setDeviceModel("Teleight UserBot");
                tdLibSettings.setApplicationVersion("2.0");
                tdLibSettings.setSystemVersion("1.0");
                tdLibSettings.setFileDatabaseEnabled(true);
                tdLibSettings.setMessageDatabaseEnabled(true);

                var clientBuilder = clientFactory.builder(tdLibSettings);

                SimpleAuthenticationSupplier<?> authenticationSupplier = null;
                if (settings instanceof UserSettings userSettings) {
                    var phoneNumber = userSettings.phoneNumber();
                    authenticationSupplier = AuthenticationSupplier.user(phoneNumber.getAsString());
                }
                if (settings instanceof BotSettings botSettings) {
                    var token = botSettings.token();
                    authenticationSupplier = AuthenticationSupplier.bot(token);
                }

                var client = clientBuilder.build(authenticationSupplier);

                this.client = client;
                tdUpdateHandler = new TdUpdateHandler(this,client);

                synchronized (AUTH_LOCK) {
                    AUTH_LOCK.notifyAll();
                }


                try {
                    client.waitForExit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();


        synchronized (AUTH_LOCK) {
            try {
                AUTH_LOCK.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
