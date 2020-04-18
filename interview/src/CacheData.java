import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheData {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<Object, Object> map = new HashMap<>();

    public void processCachedData(Object key) {
        lock.readLock().lock();
        Object object = map.get(key);
        if (null != object) {
            try {
                doProcess(object);
            } finally {
                lock.readLock().unlock();
            }
        } else {
            lock.readLock().unlock();
            lock.writeLock().lock();
            if (null == map.get(key)) {
                map.put(key, getData(key));
            }
            lock.readLock().lock();
            lock.writeLock().unlock();
            try {
                doProcess(map.get(key));
            } finally {
                lock.readLock().unlock();
            }
        }
    }

    private void doProcess(Object object) {
        System.out.println("doprocess:" + object.toString());
        //process data
    }

    private Object getData(Object key) {
        System.out.println("getData:" + key.toString());
        //get data from data source
        return key;
    }

    /**
     * simple test
     *
     * @param args
     */
    public static void main(String[] args) {
        CacheData cacheData = new CacheData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cacheData.processCachedData("key");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cacheData.processCachedData("key");
            }
        }).start();
    }
}
