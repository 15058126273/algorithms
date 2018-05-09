package common.sort;

/**
 * 计时器
 *
 * @author yjy
 * @date 2018-05-09 15:35
 */
public class TimeClocker {

    private long currentTime;

    public TimeClocker() {
        this.currentTime = System.currentTimeMillis();
    }

    public void stop(String prefix) {
        System.out.printf("%s 耗时: %d \n", prefix, System.currentTimeMillis() - currentTime);
    }

}
