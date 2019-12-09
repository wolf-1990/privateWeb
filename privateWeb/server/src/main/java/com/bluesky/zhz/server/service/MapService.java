package com.bluesky.zhz.server.service;

import com.bluesky.zhz.server.entity.bean.ConditionCoordinate;
import com.bluesky.zhz.server.entity.bean.Coordinate;

import java.util.List; /**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/27 18:30
 **/
public interface MapService {
    Coordinate getCoordinate(List<ConditionCoordinate> list);
}
