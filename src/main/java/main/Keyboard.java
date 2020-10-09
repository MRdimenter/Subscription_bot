package main;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {


    public static List<KeyboardRow> menu() {
        List<KeyboardRow> keyboardRow = new ArrayList<>();

        KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton("Подписки"));
        keyboardRowFirst.add(new KeyboardButton("Статистика"));
        keyboardRowFirst.add(new KeyboardButton("Настройки"));
        keyboardRow.add(keyboardRowFirst);

        return keyboardRow;

    }

    public static List<KeyboardRow> subscribe() {
        List<KeyboardRow> keyboardRow = new ArrayList<>();

        KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton("Добавить"));
        keyboardRowFirst.add(new KeyboardButton("Редактировать"));
        keyboardRow.add(keyboardRowFirst);

        return keyboardRow;

    }



}
