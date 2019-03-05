package pool.mythreadpool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
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
        String dumpStr = crunchifyGenerateThreadDump();
        File file = new File("/tmp/dump.log");
        if(!file.exists()){
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bytes = dumpStr.getBytes();
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myThreadPool.destroy();
    }

    public static String crunchifyGenerateThreadDump() {
        final StringBuilder dump = new StringBuilder();
        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        final ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), 100);
        for (ThreadInfo threadInfo : threadInfos) {
            dump.append('"');
            dump.append(threadInfo.getThreadName());
            dump.append("\" ");
            final Thread.State state = threadInfo.getThreadState();
            dump.append("\n   java.lang.Thread.State: ");
            dump.append(state);
            final StackTraceElement[] stackTraceElements = threadInfo.getStackTrace();
            for (final StackTraceElement stackTraceElement : stackTraceElements) {
                dump.append("\n        at ");
                dump.append(stackTraceElement);
            }
            dump.append("\n\n");
        }
        return dump.toString();
    }
}
