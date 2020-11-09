package ability;

public enum Button {
    SUBSCRIPTION("Подписки"),
    STATISCTICS("Статистика"),
    SETTINGS("Настройки"),
    ADD("Добавить"),
    EDIT("Редактировать"),
    BACK("Назад"),
    DELETE("Удалить"),
    SHOW("Показать");


    private String value;

    Button(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
