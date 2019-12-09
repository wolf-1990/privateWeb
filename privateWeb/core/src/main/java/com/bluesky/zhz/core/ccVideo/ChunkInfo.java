package com.bluesky.zhz.core.ccVideo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class ChunkInfo {

    private ConcurrentLinkedQueue<ChunkNode> chunkNodeList = new ConcurrentLinkedQueue<ChunkNode>();

    private Integer chunkSize;

    private FileInputStream fileInputStream;

    private MultipartFile videoFile;

    public ChunkInfo(){}

    public ChunkInfo(MultipartFile videoFile, Integer chunkSize){
        this(videoFile,chunkSize,null);
    }

    public ChunkInfo(MultipartFile videoFile){
        this(videoFile, CcConstant.CHUNK_SIZE,null);
    }

    private ChunkInfo(MultipartFile videoFile, Integer chunkSize,String arg){
        this.videoFile = videoFile;
        this.chunkSize = Optional.ofNullable(chunkSize).orElse(CcConstant.CHUNK_SIZE);
        splitChunk();
    }

    private boolean splitChunk(){
        Long fileSize = videoFile.getSize();
        long chunkCount = fileSize / chunkSize + 1;
        Integer iterCount = 0;
        ChunkNode chunkNode = null;
        try{
            byte[] videoFileBytes =  videoFile.getBytes();
            this.fileInputStream = (FileInputStream)videoFile.getInputStream();
            while (iterCount < chunkCount){
                long start = iterCount * chunkSize;
                long end = (iterCount + 1) * chunkSize - 1;
                Integer index = iterCount;

                if(videoFile.getSize() <= chunkSize){
                    start = 0;
                    end = fileSize - 1;
                }
                if(iterCount + 1 == chunkCount){
                    end = fileSize - 1;
                }
                int fg = (int) (end - start + 1);
                byte[] bytes = new byte[fg];
                //copy 字节数组
                System.arraycopy(videoFileBytes,Long.bitCount(start),bytes,0,bytes.length);
                chunkNode = new ChunkNode(index,start,end,bytes);
                chunkNodeList.add(chunkNode);
                iterCount++;
            }
        }catch (IOException e){
            return false;
        }
        return true;
    }

}
