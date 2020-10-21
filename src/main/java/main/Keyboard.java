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
        keyboardRowFirst.add(new KeyboardButton(Button.ADD.get()));
        keyboardRowFirst.add(new KeyboardButton(Button.EDIT.get()));
        keyboardRow.add(keyboardRowFirst);

        return keyboardRow;

    }


}
