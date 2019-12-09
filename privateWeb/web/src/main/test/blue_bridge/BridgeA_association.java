package blue_bridge;


import org.junit.Test;

import java.io.*;

/**
 * @Description 遍历文件夹下的各文件夹，分别将文件夹下的.txt 文件 ，将这些读取的内容写到其中一个文件中
 * @Author 赵赫智
 * @Date 2019/6/3 11:51
 **/
public class BridgeA_association {
    public static void main(String[] args) throws Exception {
        String targetFilePath = "D:\\学习\\蓝桥\\第六届\\Java语言C组\\summaryText.txt";
        File targetfile = new File(targetFilePath);
        if(!targetfile.exists()){
            targetfile.createNewFile();
        }
//        FileOutputStream out = new FileOutputStream(targetFilePath);
//        FileInputStream in = null;
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter
                (new FileOutputStream(targetFilePath),"gbk"));
        BufferedReader in = null;
        String path = "D:\\学习\\蓝桥\\第六届\\Java语言C组";
        File file = new File(path);
        if(!file.isDirectory()){
            throw new FileNotFoundException("找不到该目录");
        }
        File[] files = file.listFiles(File::isDirectory);
        for(int i = 0;i<files.length;i++){
            if(i!=0){
                break;
            }
            File[] files1 = files[i].listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    String name = pathname.getName();
                    String suffix = name.substring(name.lastIndexOf(".") + 1);
                    if("txt".equals(suffix)){
                        System.err.println("*********************"+name+"*************************");
                        return true;
                    }
                    return false;
                }
            });
            in = new BufferedReader(new InputStreamReader(new FileInputStream(files1[0]),"gbk"));
//            in = new FileInputStream(files1[0]);
            String str = null;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
                //写入相关文件
                out.write(str);
                out.newLine();
            }

        }
        //清楚缓存
        out.flush();
        in.close();
        out.close();
    }
    @Test
    public void aa(){
        String s = "D:\\学习\\蓝桥\\第六届\\Java语言C组\\1\\题目.txt";
        String substring = s.substring(s.lastIndexOf(".") + 1);
        System.out.println(substring);
    }
}
