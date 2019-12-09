package mytemplates;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * 不要先使用真实默认，用一个临时模型Addr,如果运行页面ok，改为Address
 * <p>Title:CreateCode</p>
 * @author  赵赫智
 * @date 2016-12-23下午3:27:08
 */
public class CreateCode {
    // 定义一个开关flag=false，不覆盖
    private final static boolean flag = !true;
    // 1.那些domain需要生成代码
    private String[] domains = { "Address" };
    String entity = "Address";
    // 2.定义固定的目录路径:都是使用相对路径,规范：路径前面都不加/,路径的后面都加/
    private final static String SRC = "src/";
    private final static String PACKAGE = "cn/zhz/demo/";
    private final static String WEBAPP = "webapp/";
    //获取首字母小写的实体用于创建包
    private  String lowerEntity = domains[0].substring(0, 1).toLowerCase() + domains[0].substring(1);
    // 3.有那些模板需要生成
    private String[] templates = {
            "ServiceImpl.java", "Service.java",
            "Controller.java","BO.java",
            "DTO.java","OneDTO.java",
            "OneRes.java","Req.java",
            "Res.java","UpdateBO.java",
            "UpdateReq.java"};
    // 4.模板文件对应的生成文件路径
    private String[] files = {
            SRC + PACKAGE + "service/impl/",SRC + PACKAGE + "service/",
            SRC + PACKAGE + "controller/",SRC   + PACKAGE + "bo/",
            SRC +PACKAGE + "dto/",SRC +PACKAGE + "dto/",
            SRC +PACKAGE + "res/",SRC +PACKAGE + "req/",
            SRC +PACKAGE + "res/",SRC +PACKAGE + "bo/",
            SRC + PACKAGE +"req/"};

    @Test
    public void create() throws Exception {
        if (templates.length != files.length) {
            throw new RuntimeException("templates.length != files.length");
        }

        // 实例化Velocity上下文
        VelocityContext context = new VelocityContext();
        // 5.外循环：domains
        for (int i = 0; i < domains.length; i++) {
            context.put("entity", domains[i]);
            context.put("entityName","地址");
            context.put("author","赵赫智");
            context.put("date", LocalDateTime.now());
            // 定义domain的首字母小写
            // 6.处理domain首字母小写
            String lowerCaseEntity = domains[i].substring(0, 1).toLowerCase() + domains[i].substring(1);
            context.put("entityDown", lowerCaseEntity);
            // 7.内循环：templates和files
            for (int j = 0; j < templates.length; j++) {
                // 8.实例化文件存放的路径
                File file = new File(files[j] + domains[i] + templates[j]);
                // 12.开关：默认情况下已经存在的文件不需要生成代码 true:覆盖所有代码
                // 如果flag==false并且当前生存文件是存在的
                if (!flag && file.exists()) {
                    // return;
                    // break;
                    continue;// 本次跳过，继续下一次循环，
                }

                // 10.判断父目录是否存在
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                System.out.println(file.getAbsolutePath());

                // 初始化并取得Velocity引擎
                VelocityEngine ve = new VelocityEngine();
                Properties p = new Properties();
                String fileDir="D:\\ideaworkspace\\myProject\\privateWeb\\web\\src\\main\\resources\\templates\\mytemplates\\";
                p.setProperty(ve.FILE_RESOURCE_LOADER_PATH, fileDir);
                ve.init(p);
                // 获取模版D:\ideaworkspace\myProject\privateWeb\web\src\main\resources\templates\mytemplates\BO.java
                Template template = ve.getTemplate(templates[j], "UTF-8");
                // 流
                FileWriter writer = new FileWriter(file);
                // 合并
                template.merge(context, writer);
                // 11.必须关闭流，写入内容
                writer.close();
            }
        }
        System.out.println("先刷新工程,修改对应表需要展示的字段,修改需要编辑保存的字段,运行测试");
        System.out.println("最后注意修改主菜单的地址运行测试");

    }

}
