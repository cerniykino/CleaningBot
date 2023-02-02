import io.github.cdimascio.dotenv.Dotenv;
import  org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
// org.telegram.telegrambots.*;

import java.sql.*;
public class MainClass {
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Cleaner());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
