package main;

import ability.Button;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {


    public static List<KeyboardRow> menu() {
        List<KeyboardRow> keyboardRow = new ArrayList<>();

        KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton(Button.SUBSCRIPTION.get()));
        keyboardRowFirst.add(new KeyboardButton(Button.STATISCTICS.get()));
        keyboardRowFirst.add(new KeyboardButton(Button.SETTINGS.get()));
        keyboardRow.add(keyboardRowFirst);

        return keyboardRow;

    }

    public static List<KeyboardRow> subscribe() {
        List<KeyboardRow> keyboardRow = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        KeyboardRow keyboardRowTwo = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton(Button.ADD.get()));
        keyboardRowFirst.add(new KeyboardButton(Button.SHOW.get()));
        keyboardRowFirst.add(new KeyboardButton(Button.DELETE.get()));
        keyboardRowTwo.add(new KeyboardButton(Button.BACK.get()));

        keyboardRow.add(keyboardRowFirst);
        keyboardRow.add(keyboardRowTwo);

        return keyboardRow;

    }

    public static List<KeyboardRow> statistics() {
        List<KeyboardRow> keyboardRow = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        KeyboardRow keyboardRowTwo = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton(Button.PER_DAY.get()));
        keyboardRowFirst.add(new KeyboardButton(Button.PER_MONTH.get()));
        keyboardRowFirst.add(new KeyboardButton(Button.PER_YEAR.get()));
        keyboardRowTwo.add(new KeyboardButton(Button.GENERAL.get()));
        keyboardRowTwo.add(new KeyboardButton(Button.BACK.get()));


        keyboardRow.add(keyboardRowFirst);
        keyboardRow.add(keyboardRowTwo);

        return keyboardRow;

    }

    /**
     *
     */
    public static List<KeyboardRow> keyboardViewSubscribe(ArrayList<String> subscribe) {
        List<KeyboardRow> keyboardRow = new ArrayList<>();
        KeyboardRow keyboardRowLine;


        for (String line : subscribe) {
            keyboardRowLine = new KeyboardRow();
            keyboardRowLine.add(line);
            keyboardRow.add(keyboardRowLine);
        }

        //Добавление кнопки назад в конец клавиатуры
        keyboardRowLine = new KeyboardRow();
        keyboardRowLine.add(Button.BACK.get());
        keyboardRow.add(keyboardRowLine);

        return keyboardRow;
    }

}
