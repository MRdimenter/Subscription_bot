package ability;

public enum TextMessage {
    StartDialog(Icon.space_invader.get() + " Добро пожаловать! " + Icon.space_invader.get() + "\n\n" +
            "Теперь вы можете отслеживать статистику по вашим платным подпискам и следить за тем сколько вы на них тратите. " + Icon.STATIC.get() + "\n\n" +
            "Для вашего удобства, я буду присылать вам уведомления за 24 часа до того как истечёт срок ваших подписок. " + Icon.calendar.get() + "\n\n" +
            "Благодаря этому вы не забудете, например, об автоматическом автопродлении и с вас не спишут деньги впустую. " + Icon.dark_sunglasses.get() + "\n\n"),


    Subscription("Здесь вы можете добавить, посмотреть и удалить ваши подписки. " + Icon.eye.get() + " " + Icon.eye.get());

    private String value;

    TextMessage(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
