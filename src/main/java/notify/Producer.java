package notify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHAOTING001 on 2019/2/18.
 */
public class Producer extends Thread {
    final List<Message> msgList = new ArrayList<>();

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(300);
                Message msg = new Message();
                synchronized (msgList) {
                    msgList.add(msg);
                    msgList.notify(); //这里只能是notify而不能是notifyAll，否则remove(0)会报java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message waitMsg() {
        synchronized (msgList) {
            if (msgList.size() == 0) {
                try {
                    msgList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return msgList.remove(0);
        }
    }
}
