package pool.mythreadpool;

/**
 * 任务接口
 * Created by ZHAOTING001 on 2018/4/11.
 */
public interface MyTask {
    void execute();

    byte[] getResult();
}
