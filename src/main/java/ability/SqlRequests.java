package ability;

public enum SqlRequests {
    ADD_USER("insert into userpeople (id, firstName, lastName, userName) VALUES (?, ?,?,?)"),
    IS_USER("SELECT EXISTS(SELECT id FROM userpeople WHERE id = ?)"),
    ADD_SUBSCRIBE("insert into subscribe (nameService, billingNumber, billingDate, firstPayment, price, idUser, firstPaymenttime) VALUES ( ?, ?, ?,? ,?,?,?)"),
    OUT_SUBSCRIBE_BY_IdUser("select nameService from subscribe where idUser = ?"),
    OUT_STATE_SUBSCRIBE_BY_IdUser("select nameService, billingNumber, billingDate, firstPayment, price, firstPaymenttime, idUser from subscribe where idUser = ?"),
    UPDATE_STATUS("update status set state = ? where iduser = ?"),
    OUT_STATUS("select state from status where idUser = ?"),
    SET_STATUS("insert into status (iduser, state) VALUES (?, ?)"),
    DELETE_SUBSCRIBE("DELETE FROM subscribe WHERE nameservice = ?"),
    OUT_ID_USERS("select id from userpeople"),
    ADD_TIME("insert into subscribe (firstPaymenttime) VALUES ( ?)"),
    UPDATE_DATE_SUBSCRIBE("update subscribe set firstPayment = ? where nameservice = ? and idUser = ?");
    private String value;

    SqlRequests(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
