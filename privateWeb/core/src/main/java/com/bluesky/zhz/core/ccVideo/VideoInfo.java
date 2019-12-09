package com.bluesky.zhz.core.ccVideo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class VideoInfo {
	
	/**
	 * 要上传的本地文件文件名
	 */
	private String name;
	
	/**
	 * 要上传的本地文件路径
	 */
	private String path;
	
	/**
	 * 要上传的本地文件大小
	 */
	private long size;
	
	/**
	 * 要上传的本地文件md5
	 */
	private String md5;
	
	/**
	 * 本次上传分配的视频id
	 */
	private String ccvid;
	
	/**
	 * 用户服务类型
	 */
	private String servicetype;
	
	/**
	 * 上传分类
	 */
	private int categoryid;
	
	/**
	 * 本次上传分配的metaurl
	 * metaurl用来查询文件状态及断点位置
	 */
	private String metaurl;
	
	/**
	 * 本次上传分配的chunkurl
	 * chunkurl用来上传文件内容块
	 */
	private String chunkurl;

	private ChunkInfo chunkInfo;
	
	public VideoInfo() {
		
	}
	
	public VideoInfo(File localFile) {
		this.name = localFile.getName();
		this.path = localFile.getPath();
		this.size = localFile.length();
	}

	public VideoInfo(MultipartFile videoFile){
		this.chunkInfo = new ChunkInfo(videoFile);
		this.name = videoFile.getOriginalFilename();
		this.size = videoFile.getSize();
		this.path = videoFile.getOriginalFilename();
	}
	
}
