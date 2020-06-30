package lesson5;

public class HomeWork5 {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        float[] arr1 = new float[size];
        float[] arr2 = new float[size];

        MyThread1(arr1);
        MyThread2(arr2);
    }

    public static void MyThread1(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = count(arr[i], i);
        }

        System.out.println("Первый метод: "+ (System.currentTimeMillis() - a));
    }

    public static void MyThread2(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1  =   new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a1[i] = count(a1[i], i);
            }
        });
        Thread t2  =   new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a2[i] = count(a2[i], i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("Второй метод: "+ (System.currentTimeMillis() - a));
    }

    public static float count(float countThread, int in) {
        return (float) (countThread * Math.sin(0.2f + in / 5) * Math.cos(0.2f + in / 5) * Math.cos(0.4f + in / 2));
    }
}
