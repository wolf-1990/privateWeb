package com.bluesky.zhz.server.service.impl;

import com.bluesky.zhz.server.entity.bean.ConditionCoordinate;
import com.bluesky.zhz.server.entity.bean.Coordinate;
import com.bluesky.zhz.server.service.MapService;
import io.jsonwebtoken.lang.Collections;

import java.util.List;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/27 18:31
 **/
public class MapServiceImpl implements MapService {
    @Override
    public Coordinate getCoordinate(List<ConditionCoordinate> list) {
        return this.calcPhonePosition(list);
    }
    //三边测量法
    // 通过三点坐标和到三点的距离，返回第4点位置
    public Coordinate calcPhonePosition(List<ConditionCoordinate> list) {
        Coordinate c4 = new Coordinate();
        if(Collections.isEmpty(list) || list.size()!=3){
            return c4;
        }
        ConditionCoordinate c1 = null,c2 = null,c3=null;
        c1 = list.get(0);
        c2 = list.get(1);
        c3 = list.get(2);
        double x1 = c1.getLatitude(); double y1 =c1.getLongitude(); double d1 = c1.getDistance();
        double x2 = c2.getLatitude(); double y2 =c2.getLongitude(); double d2 = c2.getDistance();
        double x3 = c3.getLatitude(); double y3 =c3.getLongitude(); double d3 = c3.getDistance();
        double[] d = {0.0, 0.0};
        double a11 = 2 * (x1 - x3);
        double a12 = 2 * (y1 - y3);
        double b1 = Math.pow(x1, 2) - Math.pow(x3, 2)
                + Math.pow(y1, 2) - Math.pow(y3, 2)
                + Math.pow(d3, 2) - Math.pow(d1, 2);
        double a21 = 2 * (x2 - x3);
        double a22 = 2 * (y2 - y3);
        double b2 = Math.pow(x2, 2) - Math.pow(x3, 2)
                + Math.pow(y2, 2) - Math.pow(y3, 2)
                + Math.pow(d3, 2) - Math.pow(d2, 2);

        d[0] = (b1 * a22 - a12 * b2) / (a11 * a22 - a12 * a21);
        d[1] = (a11 * b2 - b1 * a21) / (a11 * a22 - a12 * a21);
        c4.setLatitude(d[0]);
        c4.setLongitude(d[1]);
        return c4;
    }
}
