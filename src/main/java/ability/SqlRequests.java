package ability;

public enum SqlRequests {
    ADD_USER("insert into userpeople (id, firstName, lastName, userName) VALUES (?, ?,?,?)"),
    IS_USER("SELECT EXISTS(SELECT id FROM userpeople WHERE id = ?)"),
    ADD_SUBSCRIBE("insert into subscribe (nameService, billingNumber, billingDate, firstPayment, price, idUser) VALUES ( ?, ?, ?,? ,?,?)");

    private String value;

    SqlRequests(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
