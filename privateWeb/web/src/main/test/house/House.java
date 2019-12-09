package house;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/10/25 10:38
 **/
@Data
public class House {
    @ApiModelProperty("所属城市")
    private String cityName;
    @ApiModelProperty("房屋均价")
    private Double avgPrice;
    @ApiModelProperty("热门小区")
    private String[] hotVillage;
}
