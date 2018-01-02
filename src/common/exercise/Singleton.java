package common.exercise;

/**
 * @description: 单例模式
 * 实现单例模式, 支持并发
 * @author: yjy
 * @datetime: 2018/01/02 9:57
 */
public class Singleton {

    public static void main(String[] args) {
        Enumer.INSTANCE.getInstance().print1();
    }

    /**
     * 利用枚举获得单例实例
     */
    private enum Enumer {

        INSTANCE;
        private Util instance;
        Enumer() {
            instance = new Util();
        }
        private Util getInstance() {
            return instance;
        }

        private class Util {

            private void print1() {
                System.out.print(1);
            }

        }

    }

}
