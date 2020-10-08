package Activity;

import java.util.logging.Logger;

public class Stable implements Activity {
    private static Logger log = Logger.getLogger(Stable.class.getName()); //логирование
    @Override
    public void state() {
        log.info("--- State Stable ---");

    }
}
