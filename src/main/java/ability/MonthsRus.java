package ability;


import java.util.logging.Logger;

public enum MonthsRus {
    янв, фев, мар, апр, мая, июн, июл, авг, сент, окт, ноя, дек;

    private static Logger log = Logger.getLogger(MonthsRus.class.getName());

    private MonthsRus() {
    }

    private static final MonthsRus[] ENUMS = values();

    public static MonthsRus of(int monthsRus) {

        if (monthsRus >= 1 && monthsRus <= 12) return ENUMS[monthsRus - 1];
        else {
            log.warning("Invalid value for MonthOfYear: " + monthsRus);
            throw new NullPointerException();
        }
    }


}
