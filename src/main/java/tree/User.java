package tree;

import java.math.BigDecimal;

/**
 * Created by ZHAOTING001 on 2018/11/21.
 */
public class User {
    public static void main(String[] args) {
        BigDecimal pool = new BigDecimal(18179);
        BigDecimal gap = new BigDecimal(50);
        BigDecimal price = new BigDecimal(3000);
        int count = 50;
//        for (int i = 0; i < 41; i++) {
//            BigDecimal pool_in = price.multiply(new BigDecimal(0.4)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
//            pool = pool.add(pool_in);
//            price = price.add(gap);
//        }
        BigDecimal percent = BigDecimal.ONE.divide(new BigDecimal(count), 10, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal benefit = BigDecimal.ZERO;
        for (int i = 0; i < 123; i++) {
            BigDecimal pool_in = price.multiply(new BigDecimal(0.4)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
            pool = pool.add(pool_in);
            benefit = benefit.add(price.multiply(new BigDecimal(0.37)).multiply(percent).setScale(4, BigDecimal.ROUND_HALF_DOWN));
            count++;
            percent = BigDecimal.ONE.divide(new BigDecimal(count), 10, BigDecimal.ROUND_HALF_DOWN);
            price = price.add(gap);
        }
        System.out.println(pool.multiply(new BigDecimal(0.5)));
        BigDecimal extra = pool.multiply(new BigDecimal(0.25)).multiply(percent);
        System.out.println(extra);
        System.out.println(price);
        System.out.println(benefit);
        BigDecimal divide = new BigDecimal(50).divide(new BigDecimal(123),4, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(divide);
    }


}
