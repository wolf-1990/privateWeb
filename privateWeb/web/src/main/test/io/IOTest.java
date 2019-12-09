package io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/11/13 19:24
 **/
public class IOTest {
    private Integer num =0;
    @Test
    public void test() throws IOException {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(8);


        //查找D盘所在目录下的图片并打印路劲
        String path="D:/";
        File rootFile = new File(path);
        String inputPath = "D://image";
        File file = new File(inputPath);
        if(!file.exists()){
            boolean newFile = file.createNewFile();
        }
        LocalDateTime now = LocalDateTime.now();

        try {
            interatorFiles(rootFile, inputPath);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        LocalDateTime now1 = LocalDateTime.now();
        int second = now.getSecond();
        int second1 = now1.getSecond();
        int buf = second1-second;
        System.out.println("获取文件数："+num);
        System.out.println("执行时间差："+buf+"秒");

    }

    private void interatorFiles(File rootFile, String inputPath) throws Throwable{

        boolean directory = rootFile.isDirectory();
//        System.out.println(rootFile.getAbsolutePath());
        if(num >=500){
            return;
        }
        if(directory){
            File[] files = rootFile.listFiles();
            if(files != null && files.length >0){
                for (File file : files) {
                    String nameOne = rootFile.getName();
                    if(nameOne.equals("ideaworkspace") || nameOne.equals("Program Files") || nameOne.equals("Program Files (x86)")){
                        continue;
                    }
                    interatorFiles(file, inputPath);
                }
            }
        }else if(rootFile.isFile()){
//            System.out.println(rootFile.getPath());
            String name = rootFile.getName();
            long length = rootFile.length();
            boolean maxBoolean = length <= 900000; //500000 Byte 500KB  1000 KB 1M
            boolean minBoolean = length >= 10000;
            String matchQQ = "^[0-9A-Z]*$";//QQ 图片
            String matchWechat = "^\\$+[0-9A-Z]*$";//微信
            String matchEmail = "^[a-z]*$";//QQ邮箱

            String nameStr = name.split("\\.")[0];
            boolean matches = nameStr.matches(matchEmail);

//            boolean jpgMatch = name.split("\\.")[1].equals("jpg"); 照片 & 截图
//            boolean pngMatch = name.split("\\.")[1].equals("png"); logo  之类
//            boolean pngMatch = name.split("\\.")[1].equals("GIF");  表情

//            && b && b1
            if(name.contains(".") && name.split("\\.")[1].equals("gif")
                    && maxBoolean && minBoolean ){

                File file = new File(inputPath+"/"+name);
                FileOutputStream fileWrite = new FileOutputStream(file);//写入
                FileInputStream fileReader = new FileInputStream(rootFile);//读取
                int num = 0;
                byte[] bytes = new byte[1024*10];
                while ((num=fileReader.read(bytes))!= -1){
                    fileWrite.write(bytes);
                }
                System.out.println(this.num+"."+name+"写入成功");
                this.num++;
                fileWrite.close();
                fileReader.close();
            }
        }
    }
    @Test
    public void aa(){
        String match = "^\\$+[0-9A-Za-z]*$";
        String input = "a0Aa";
        System.out.println(input.matches(match));
    }

}
