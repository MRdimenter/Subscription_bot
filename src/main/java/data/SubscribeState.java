package data;

import java.util.HashMap;
import java.util.Map;

//Cостояние подписки
public class SubscribeState {
    private double total; //Общая стоимость подписок
    private Map<String, Double> subscribeState;

    public SubscribeState() {
        this.subscribeState = new HashMap<>();

    }

    public void setSubscribeState(String nameService, double price) {
        subscribeState.put(nameService, price);
    }

    public double getTotal() {
        for (double sum : subscribeState.values()) total += sum;
        return total;
    }

    public Map<String, Double> getSubscribeState() {
        return subscribeState;
    }

}

