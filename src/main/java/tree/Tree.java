package tree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ZHAOTING001 on 2018/11/21.
 */
public class Tree {
    private AtomicLong mianPool;

    public static void main(String[] args) {
        int init= 500;
        int gap = 50;
        for (int i = 0; i < 50; i++) {
            init = init+50;
        }
        System.out.println(init);
    }

}
