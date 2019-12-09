package movie;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TOO 电影
 * @Author 赵赫智
 * @Date 2019/10/25 13:48
 **/
@Data
public class Movie implements Comparable<Movie>{
    @ApiModelProperty("片名")
    private String name;
    @ApiModelProperty("影评")
    private String filmReview;
    @ApiModelProperty("评分")
    private Double score;
    @ApiModelProperty("宣传图路劲")
    private String publicityMapUrl;
    @ApiModelProperty("类别名称")
    private String typeName;

    @Override
    public String toString() {
        return this.name+" "+this.score;
    }

    @Override
    public int compareTo(Movie o) {
        double v = o.getScore() - this.getScore();
        int result = 0;
        if(v>0){
            result= 1;
        }else if(v<0){
            result = -1;
        }else {
            result = 0;
        }
        return result;
    }
}
