package pool;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ZHAOTING001 on 2018/4/11.
 */
public class ResourceLoader implements MyTask {

    private String resourceName;

    public ResourceLoader() {
    }

    public ResourceLoader(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public void execute() {
        System.out.println("ResourceLoader start execute, resourceName: " + resourceName);
    }

    @Override
    public byte[] getResult() {
        return new byte[0];
    }

    public static void main(String[] args) {

        List<String> resourceList = Arrays.asList("resource1", "resource2", "resource3",
                "resource4", "resource5", "resource6");


        MyThreadPool myThreadPool = new MyThreadPool(resourceList.size());

        resourceList.forEach(s -> {
            MyExecutor executor = myThreadPool.getExecutor();
            executor.setTask(new ResourceLoader(s));
            executor.startTask();
        });

        myThreadPool.destroy();
    }
}
