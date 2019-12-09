package coreJava.genericity;

import com.alibaba.fastjson.JSON;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 13:41
 **/
public class Client {
    public static void main(String[] args) {
        //流程基本信息
        ApplyRequest applyRequest = new ApplyRequest();
        applyRequest.setApplyId("2333");
        applyRequest.setApplyName("applyName");
        applyRequest.setApplyType(1);
        applyRequest.setApplyBaicInfo("basic infomation");
        /**
         * 流程内容 类型一：岗位信息
         */
        PostInfo postInfo = new PostInfo();
        postInfo.setPostCode("postCode");
        postInfo.setPostName("postName");

        applyRequest.setApplyContent(postInfo);
        System.out.println("=============流程内容一：岗位================");
        System.out.println(JSON.toJSONString(applyRequest));

        /**
         * 流程内容 类型二 ：报表信息
         */
        RptBaseDto reportEntity = new RptBaseDto();
        reportEntity.setReportId("reportId");
        reportEntity.setVersionId("versionid");
        applyRequest.setApplyType(2);
        applyRequest.setApplyContent(reportEntity);
        System.out.println("=============流程内容二：报表================");

        System.out.println(JSON.toJSONString(applyRequest));
    }
}
