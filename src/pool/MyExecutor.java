package pool;

/**
 * 执行器接口
 * Created by ZHAOTING001 on 2018/4/11.
 */
public interface MyExecutor {
    void setTask(MyTask task);

    MyTask getTask();

    void startTask();
}
