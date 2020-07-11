package algo;

import org.apache.tools.ant.util.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SubArrayK {

    ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(50);
    ExecutorService executorService = Executors.newFixedThreadPool(20);


    public static void main(String[] args) {
        String s = null;
        try {
            s = FileUtils.readFully(new BufferedReader(new FileReader("src/main/resources/arr.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int K = 5837033;
        String[] split = s.split(",");
        int[] A = Arrays.stream(split).mapToInt(s1 -> Integer.parseInt(s1.trim())).toArray();
        SubArrayK subArrayK = new SubArrayK();
        long start = System.currentTimeMillis();
        int i = subArrayK.shortestSubarray(A, K);
        System.out.println("cost:" + (System.currentTimeMillis() - start) + ",length:" + i);
    }

    public int shortestSubarray(int[] A, int K) {
        if (A == null || A.length <= 0) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int max_idx = 0;
        int sum = 0;
        boolean subArrayExsit = false;
        int minimalLength = -1;
        ArrayList<Integer> list = new ArrayList<>(A.length);
        int[] P = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            //取最大
            if (A[i] > max) {
                max = A[i];
                max_idx = A[i];
            }
            P[i + 1] = P[i] + A[i];
            if (P[i + 1] >= K) {
                subArrayExsit = true;
                int currentLength = i + 1;
                if (minimalLength < 0) {
                    minimalLength = currentLength;
                }
                if (minimalLength > 0 && currentLength < minimalLength) {
                    minimalLength = currentLength;
                }
            }
            list.add(A[i]);
        }
        //是否有大于K的子元素
        if (max >= K) {
            return 1;
        }

        int minimalStep = 1;
        //是否存在子数组和 >= K
        if (!subArrayExsit) {
            int sumR = 0;
            list.sort(Collections.reverseOrder());
            for (Integer i : list) {
                sumR += i;
                if (sumR >= K) {
                    minimalStep = list.indexOf(i) + 1;
                    break;
                }
            }
            //子数组不存在
            if (sumR < K) {
                return -1;
            }
        }

        if (minimalLength == -1) {
            minimalLength = A.length;
        }

        //存在子数组
        int currStep = minimalStep;
        for (int i = 1; i < A.length - minimalLength; i++) {
            for (int j = i + currStep; j < A.length && j < i + minimalLength; j++) {
                int isum = P[j] - P[i];
                if (isum >= K) {
                    int currLength = j - i;
                    if (currLength < minimalLength) {
                        minimalLength = currLength;
                    }
                }
            }
        }

        return minimalLength;
    }
}
