package src;

import java.util.concurrent.atomic.AtomicInteger;

public class LOCK {
    public static final Object CONN_LOCK = new Object();
    public static final AtomicInteger ID_LOCK = new AtomicInteger(0);

    public static int nextId(){
        return ID_LOCK.incrementAndGet();
    }

}
