package com.bluesky.zhz.core.ccVideo;

import lombok.Data;

@Data
public class ChunkNode {

    private Integer index;
    private Boolean isFinishRead = false;
    byte[] bytes;
    private Long start;
    private Long end;

    public ChunkNode(){}

    public ChunkNode(Integer index,Long start,Long end, byte[] bytes){
        this.index = index;
        this.start = start;
        this.end = end;
        this.bytes = bytes;
    }

}
