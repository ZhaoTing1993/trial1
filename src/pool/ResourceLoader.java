package pool;

/**
 * Created by ZHAOTING001 on 2018/4/11.
 */
public class ResourceLoader implements MyTask {
    @Override
    public void execute() {
        System.out.println("ResourceLoader start execute");
    }

    @Override
    public byte[] getResult() {
        return new byte[0];
    }
}
