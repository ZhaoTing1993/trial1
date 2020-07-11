package algo;

import org.apache.tools.ant.util.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class SubArrayKTreeMap {
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
        SubArrayKTreeMap subArrayK = new SubArrayKTreeMap();
        long start = System.currentTimeMillis();
        int i = subArrayK.shortestSubarray(A, K);
        System.out.println("cost:" + (System.currentTimeMillis() - start) + ",length:" + i);
    }

    public int shortestSubarray(int[] A, int K) {
        int sum = 0, result = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, -1);
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            Map.Entry<Integer, Integer> floorEntry = map.floorEntry(sum - K);
            if (floorEntry != null && i - floorEntry.getValue() < result)
                result = i - floorEntry.getValue();
            while (map.size() > 0 && map.lastKey() > sum) map.pollLastEntry();
            map.put(sum, i);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

}
