package baby;

import java.util.Random;

/**
 * 蓄水池算法
 */
public class ReservoirSampling {

    private int poolSize = 100;
    private int[] pools = new int[poolSize];
    private Random random = new Random();


    public static void main(String[] args) {

        ReservoirSampling reservoirSampling = new ReservoirSampling();
        reservoirSampling.initPool();

        int[] sampling = reservoirSampling.sampling(10);
        for (int i : sampling) {
            System.out.print(i + " ");
        }
    }

    public void initPool() {
        for (int i = 0; i < pools.length; i++) {
            pools[i] = i;
        }
    }

    public int[] sampling(int k) {
        int[] retArray = new int[k];
        for (int i = 0; i < k; i++) {
            retArray[i] = pools[i];
        }

        for (int i = k; i < poolSize; i++) {
            int d = random.nextInt(i - 1);
            float rate = d / (k + 0.0f);

            if (rate < 1) {
                retArray[d] = pools[i];
            }
        }
        return retArray;
    }

}
