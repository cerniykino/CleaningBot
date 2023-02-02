import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Driver;
public class Cleaner extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            Chat chat = update.getMessage().getChat();
            System.out.println(chat.getUserName());

            Cleaner cleaner = new Cleaner();

            List<String> stringList = new ArrayList<>();
            stringList.add("who");
            stringList.add("what");
            cleaner.sendPoll(chat, "o[ihiuhoiuhoiuh",stringList );

            SendMessage message = new SendMessage();
            message.setChatId(chat_id);
            message.setText("hello");

            try {
                execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }
    }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return "Cleaner";
    }

    @Override
    public String getBotToken() {
        //using Dotenv for open environment file and get environment variable
        Dotenv dotenv = Dotenv.configure().directory("src").filename("cleaningbot.env").load();
        return dotenv.get("BOT_TOKEN");
    }
    public void sendPoll(Chat chat, String question, List<String> options){
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(chat.getId().toString());
        sendPoll.setQuestion(question);
        sendPoll.setOptions(options);
        try {
            Message message = execute(sendPoll);
            Poll poll = message.getPoll();
            System.out.println("Poll");
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }


}
