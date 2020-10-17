package data;

public class Subscribe {
    private long UserId;
    private String name;
    private String billing;
    public boolean flagactivity = false;


    public Subscribe() {

    }


    public long getUserId() { return UserId; }

    public String getName() {
        return name;
    }

    public String getBilling() {
        return billing;
    }

    public void setUserId(long userId) { UserId = userId; }

    public void setName(String name) {
        this.name = name; }

    public void setBilling(String billing) { this.billing = billing; }
}
