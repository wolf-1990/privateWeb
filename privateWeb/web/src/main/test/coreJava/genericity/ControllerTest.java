package coreJava.genericity;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 13:55
 **/
@Controller
public class ControllerTest {
    @RequestMapping(value = "/testFX", method = RequestMethod.POST)
    public ResponseObject testFX(@RequestBody ApplyRequest applyRequest) {
        System.out.println(JSON.toJSONString(applyRequest));
        Integer applyType = applyRequest.getApplyType();
        String instance = JSON.toJSONString(applyRequest);
        switch (applyType){
            case 1:
                ApplyRequest<PostInfo> applyRequest1 = applyRequest;
                PostInfo postInfo = applyRequest1.getApplyContent();
                return new ResponseObject(JSON.toJSONString(postInfo));
            case 2:
                ApplyRequest<RptBaseDto> applyRequest2 = applyRequest;
                RptBaseDto rptBaseDto = applyRequest2.getApplyContent();
                return new ResponseObject(JSON.toJSONString(rptBaseDto));
            default:
                return null;
        }
    }
}
