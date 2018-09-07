import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ZHAOTING001 on 2018/4/10.
 */
public class Lambda {
    public static void main(String[] args) {
        //traverse a list
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);

        System.out.println("========= traditional ======");
        //old style
        for (String player : players) {
            System.out.println(player + "; ");
        }

        System.out.println("========= lambda ===========");
        //lambda expression & functional operation
        players.forEach(s -> {
            System.out.println(s + ";");
        });

        //implements Runnable

        //old style
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new Task started !");
            }
        }).start();

        //with Lambda
        new Thread(() -> {
            System.out.println("new Task written by Lambda !");
        }).start();


        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1");
            }
        };

        Runnable r2 = () -> {
            System.out.println("r2");
        };

        new Thread(r1).start();
        r2.run();

        //Collection Sort
        Collections.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });

        //lambda
        Collections.sort(players, (o1, o2) -> o1.compareTo(o2));
        //method reference
        Collections.sort(players, String::compareTo);



    }
}
