package pl.kpp.tovarna.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kpp.tovarna.TovarnaApplication;

public final class Loggers {

    private Loggers() {}

    public static Logger forEnclosingClass() {
        var st = Thread.currentThread().getStackTrace();
        if (st.length > 2)
            return LoggerFactory.getLogger(st[2].getClassName());

        return LoggerFactory.getLogger(TovarnaApplication.class);
    }

}
