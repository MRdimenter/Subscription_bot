package ability;

import com.vdurmont.emoji.EmojiParser;

public enum Icon {
    PLUS(":heavy_plus_sign:"),
    MINUS(":heavy_minus_sign:"),
    CHECK(":white_check_mark:"),
    NOT(":x:"),
    DOUBT(":zzz:"),
    FLAG(":checkered_flag:"),
    STATIC(":memo:"),
    LOL(":upside_down:"),
    space_invader(":space_invader:"),
    dark_sunglasses(":dark_sunglasses:"),
    alarm_clock(":alarm_clock:"),
    bell(":bell:"),
    calendar(":calendar:\n"),
    eye(":eye:");

    private String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Icon(String value) {
        this.value = value;
    }
}
