package ability;

public enum Button {
    SUBSCRIPTION("Подписки"),
    STATISCTICS("Статистика"),
    SETTINGS("Настройки"),
    ADD("Добавить"),
    EDIT("Редактировать"),
    BACK("Назад"),
    DELETE("Удалить"),
    SHOW("Показать"),
    PER_DAY("За день"),
    PER_MONTH("За месяц"),
    PER_YEAR("За год"),
    GENERAL("Общая"),
    HELP("Помощь");

    private String value;

    Button(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
