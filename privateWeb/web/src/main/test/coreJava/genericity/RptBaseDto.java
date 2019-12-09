package coreJava.genericity;

import lombok.Data;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 13:38
 **/
@Data
public class RptBaseDto {
    //报表Id
    private String reportId;
    //报表版本id
    private String versionId;

    public RptBaseDto() {
    }

    public RptBaseDto(String reportId, String versionId) {
        this.reportId = reportId;
        this.versionId = versionId;
    }

    @Override
    public String toString() {
        return "RptBaseDto{" +
                "reportId='" + reportId + '\'' +
                ", versionId='" + versionId + '\'' +
                '}';
    }
}
