package algo;

import org.apache.tools.ant.util.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SubArrayKOfficailSolution {

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
        SubArrayKOfficailSolution subArrayK = new SubArrayKOfficailSolution();
        long start = System.currentTimeMillis();
        int i = subArrayK.shortestSubarray(A, K);
        System.out.println("cost:" + (System.currentTimeMillis() - start) + ",length:" + i);
    }

    public int shortestSubarray(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N + 1];
        for (int i = 0; i < N; ++i)
            P[i + 1] = P[i] + (long) A[i];

        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N + 1; // N+1 is impossible
        Deque<Integer> monoq = new LinkedList(); //opt(y) candidates, as indices of P

        for (int y = 0; y < P.length; ++y) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()])
                monoq.removeLast();
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)
                ans = Math.min(ans, y - monoq.removeFirst());

            monoq.addLast(y);
        }

        return ans < N + 1 ? ans : -1;
    }
}

