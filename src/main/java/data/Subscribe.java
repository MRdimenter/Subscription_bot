package data;

public class Subscribe {
    private long UserId;
    private String name;
    private String billing;

    public Subscribe(long userId, String name, String billing) {
        UserId = userId;
        this.name = name;
        this.billing = billing;
    }

    public Subscribe() {

    }

    public Subscribe(long userId) {
        UserId = userId;
    }

    public Subscribe(String name) {
        this.name = name;
    }

    public long getUserId() {
        return UserId;
    }

    public String getName() {
        return name;
    }

    public String getBilling() {
        return billing;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }
}
