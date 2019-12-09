package com.bluesky.zhz.core.ccVideo;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class Uploader {

    /**
     * 文件信息类，包括本地文件属性和本次上传信息
     */
    private VideoInfo videoInfo;

    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public Uploader(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    /**
     * 上传入口方法
     */
    public boolean upload() {
        //计算及校验md5可能会比较耗时
        //如果不校验文件完整性的话，可以不计算文件md5, 请删除或注释下面计算和设置md5的代码行
        File localFile = new File(videoInfo.getPath());
        String md5 = HashUtil.md5(localFile);
        //String md5 = HashUtil.md5(videoInfo.getChunkInfo());
        if (md5 == null) {
            log.info(String.format("Can't get file md5, file path: %s, exit...", videoInfo.getPath()));
            return false;
        }
        videoInfo.setMd5(md5);

        //上传文件之前，要确保已经获取系统分配的上传信息(videoid, metaurl, chunkurl等)
        if (!isUploadInfoAssigned()) {
            log.info("Can't get upload info, exit...");
            return false;
        }
        //上传本地文件
        boolean success = doResume();
        //boolean success = doResumeAwared();
        if (success) {
            log.info("Upload success!");
            return true;
        } else {
            log.info("Upload failed?!");
            return false;
        }
    }

    /**
     * 判断是否已经为本次上传分配视频id(videoid)及上传路径(metaurl, chunkurl):
     * 没有的话，请求http://spark.bokecc.com/api/video/create/v2 获得上传信息，
     * 请求失败时，会重试若干次；
     *
     * @return 成功获得上传信息，返回true；否则，返回false
     */
    private boolean isUploadInfoAssigned() {
        if (videoInfo.getCcvid() != null && videoInfo.getMetaurl() != null) {
            //已获取videoid, metaurl, chunkurl等上传信息
            return true;
        }
        //获取视频上传信息失败的话,重试若干次
        int uploadInfoRetryCount = 6;
        while (uploadInfoRetryCount > 0) {
            if (getUploadInfo()) {
                return true;
            }
            uploadInfoRetryCount--;
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 用本地待上传的文件信息, 请求http://spark.bokecc.com/api/video/create/v2,
     * 验证该文件是否可以上传并获得上传文件的先决条件(系统分配的视频id, 上传路径等关键信息)
     * <br>
     * 正常响应示例(已格式化显示): <br>
     * <code>
     * {<br>
     * "uploadinfo": {<br>
     * "videoid": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",<br>
     * "userid": "xxxxxx",<br>
     * "servicetype": "xxxxxxxxxxxxxxxx",<br>
     * "metaurl": "http://1-15-vacombiner.bokecc.com/servlet/uploadmeta/v2",<br>
     * "chunkurl": "http://1-15-vacombiner.bokecc.com/servlet/uploadchunk/v2"<br>
     * }<br>
     * }<br>
     * </code>
     * <br>
     * 异常响应示例(已格式化显示): <br>
     * <code>
     * {<br>
     * "error":" error message "<br>
     * }<br>
     * </code>
     *
     * @return 成功获取上传信息, 返回true; 否则, 返回false
     */
    private boolean getUploadInfo() {
        Map<String, String> queryMap = new HashMap<String, String>();
        //用户id
        queryMap.put("userid", CcConstant.CC_USER_ID);
        queryMap.put("title", videoInfo.getName());
        queryMap.put("description", videoInfo.getName());
        queryMap.put("tag", videoInfo.getName());
        queryMap.put("filename", videoInfo.getName());
        queryMap.put("filesize", String.valueOf(videoInfo.getSize()));
        //上传转码完成之后的回调地址
        //queryMap.put("notify_url", "your notify url");

        //根据用户key对请求参数进行THQS加密
        String qs = QueryStringUtil.createHashedQueryString(queryMap, System.currentTimeMillis(), CcConstant.VIDEO_API_KEY);
        HttpURLConnection conn = null;
        String response = null;
        try {
            conn = (HttpURLConnection) new URL(CcConstant.UPLOADINFO_URL + "?" + qs).openConnection();
            conn.setConnectTimeout(30 * 1000);
            conn.setReadTimeout(30 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setRequestProperty("accept", "text/json");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4)");
            conn.setRequestProperty("Charsert", "UTF-8");

            conn.connect();
            int code = conn.getResponseCode();
            if (200 != code) {
                conn.disconnect();
                return false;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            conn.disconnect();
            response = sb.toString();

            Gson gson = new Gson();
            SparkResponse spark = gson.fromJson(response, SparkResponse.class);


            videoInfo.setCcvid(spark.getVideoid());
            videoInfo.setServicetype(spark.getServicetype());
            String metaurl = String.format("%s?ccvid=%s&uid=%s&servicetype=%s&filename=%s&filesize=%s",
                    spark.getMetaurl(), videoInfo.getCcvid(), CcConstant.CC_USER_ID, videoInfo.getServicetype(),
                    videoInfo.getName(), videoInfo.getSize());
            if (videoInfo.getMd5() != null) {
                metaurl = metaurl + "&md5=" + videoInfo.getMd5();
            }
            videoInfo.setMetaurl(metaurl);
            videoInfo.setChunkurl(String.format("%s?ccvid=%s", spark.getChunkurl(), videoInfo.getCcvid()));
            //可以将VideoInfo本地持久化，程序启动后可以继续上传
            return true;
        } catch (JsonParseException je) {
            je.printStackTrace();
            log.info(String.format("Error uploadInfo response: %s", response));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return false;
    }

    /**
     * 调用servlet/uploadmeta/v2接口,查询文件上传状态及断点位置, 查询失败时重试若干次
     *
     * @return json格式响应结果的封装实例Res, 没有响应结果或者响应格式有误时返回null
     */
    private UploadResponse doCheck() {
        //查询文件上传状态及断点位置
        int checkCount = 6;
        while (checkCount > 0) {
            UploadResponse res = checkRange();
            if (res != null) {
                return res;
            }

            checkCount--;
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //遍历上传文件节点
    /*private boolean doResumeAwared(){
        ConcurrentLinkedQueue<ChunkNode> chunkNodes = getVideoInfo().getChunkInfo().getChunkNodeList();
        Map<String,Object> returnMap;
        for(ChunkNode chunkNode : chunkNodes){
            if(!chunkNode.getIsFinishRead()){
                returnMap =  doResume(chunkNode);
                chunkNode.setIsFinishRead(true);
            }
        }
        return true;
    }*/

    /**
     * 如果在发送文件块的过程中出现中断，可再次调用servlet/uploadmeta/v2接口,
     * 以查询当前的文件状态及断点位置,并从断点位置处继续上传
     * @param  //ChunkNode chunkNode
     * @return 上传成功，返回true; 否则,返回false;
     */
    private boolean doResumea(){
        Map<String,Object> returnMap = new HashMap();
        ConcurrentLinkedQueue<ChunkNode> chunkNodes = getVideoInfo().getChunkInfo().getChunkNodeList();


        //在上传文件块之前，必须先调用uploadmeta/v2接口查看文件当前状态及断点位置
        UploadResponse res = doCheck();
        int retryCount = 20;
        while (retryCount > 0) {
            if (res == null) {
                log.info("Get no response from check, exit...");
                returnMap.put("code","xx");
                return false;
            }
            int result = res.getResult();
            returnMap.put("isSuccess",result);

            /*if(RESULT_OK == result){
                // 文件仍在上传状态中，成功返回“断点位置”
                long uploaded = res.getReceived();
                returnMap.put("uploaded",uploaded);
                return returnMap;
            } else*/
            if (UploadResponse.RESULT_SUCCESS == result) {
                //上传成功
                return false;
            } else if (UploadResponse.RESULT_FAILED == result) {
                //本条ccvid对应的文件记录已经上传失败，请申请新的ccvid及上传路径，重新开启上传流程
                log.info(String.format("Upload failed, msg: %s", res.getMsg()));
                return false;
            } else if (UploadResponse.RESULT_INVALID_PARAM == result) {
                //请求参数错误, 请核对错误信息，重新上传
                log.info(String.format("Invalid request parameter, msg: %s", res.getMsg()));
                return false;
            } else if (UploadResponse.RESULT_INTERNAL_ERROR == result) {
                //服务器内部错误，请稍后重试，或联系客服
                log.info(String.format("Server error, retry later, msg: %s", res.getMsg()));
                res = doCheck();
                retryCount--;
                continue;
            } else if(UploadResponse.RESULT_OK == result){
                /*long uploaded = res.getReceived();

                long start = uploaded;
                long end = start + CHUNK_SIZE - 1;
                end = end > videoInfo.getSize() - 1 ? videoInfo.getSize() - 1 : end;
                int chunkSize = (int) (end - start + 1);
                byte[] bytes = new byte[chunkSize];*/
                try {
                    //access.seek(start);
                    //int readed = access.read(bytes, 0, bytes.length);
                    //if (readed < chunkSize) {
                    //    throw new IOException(String.format("%s bytes expected to read, but only readed:%s, chunkStart:%s, filesize:%s", chunkSize, readed, start, videoInfo.getSize()));
                    //}
                    //res = postChunk(start, end, localFile, bytes);
                    for(ChunkNode chunkNode1 : chunkNodes){
                        if(!chunkNode1.getIsFinishRead()){
                            //returnMap =  doResume(chunkNode);
                            res = postChunk(chunkNode1.getStart(), chunkNode1.getEnd(), null, chunkNode1.getBytes());
                            chunkNode1.setIsFinishRead(true);
                            if (res == null) {
                                throw new IOException(String.format("Post file chunk error, no res returned, start:%s, end:%s", 1, 2));
                            }
                        }
                    }
                    //res = postChunk(chunkNode.getStart(), chunkNode.getEnd(), null, chunkNode.getBytes());
                    /*if (res == null) {
                        throw new IOException(String.format("Post file chunk error, no res returned, start:%s, end:%s", 1, 2));
                    }*/
                    return true;
                } catch (IOException e) {
                    //log.info(String.format("Read local file error, start=%s,len=%s,ccvid=%s,path:%s", start, chunkSize, videoInfo.getCcvid(), videoInfo.getPath()));
                    e.printStackTrace();
                    retryCount--;
                    res = doCheck();
                }
            }

        }
        return true;
    }


    /**
     * 如果在发送文件块的过程中出现中断，可再次调用servlet/uploadmeta/v2接口,
     * 以查询当前的文件状态及断点位置,并从断点位置处继续上传
     *
     * @return 上传成功，返回true; 否则,返回false;
     */
    private boolean doResume() {
        File localFile = new File(videoInfo.getPath());
        RandomAccessFile access = null;
        try {
            access = new RandomAccessFile(localFile, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.info(String.format("Read local file error, path: %s", videoInfo.getPath()));
            return false;
        }

        //在上传文件块之前，必须先调用uploadmeta/v2接口查看文件当前状态及断点位置
        UploadResponse res = doCheck();
        int retryCount = 20;
        while (retryCount > 0) {
            if (res == null) {
                log.info("Get no response from check, exit...");
                return false;
            }
            int result = res.getResult();
            if (UploadResponse.RESULT_SUCCESS == result) {
                //上传成功
                return true;
            } else if (UploadResponse.RESULT_FAILED == result) {
                //本条ccvid对应的文件记录已经上传失败，请申请新的ccvid及上传路径，重新开启上传流程
                log.info(String.format("Upload failed, msg: %s", res.getMsg()));
                return false;
            } else if (UploadResponse.RESULT_INVALID_PARAM == result) {
                //请求参数错误, 请核对错误信息，重新上传
                log.info(String.format("Invalid request parameter, msg: %s", res.getMsg()));
                return false;
            } else if (UploadResponse.RESULT_INTERNAL_ERROR == result) {
                //服务器内部错误，请稍后重试，或联系客服
                log.info(String.format("Server error, retry later, msg: %s", res.getMsg()));
                res = doCheck();
                retryCount--;
                continue;
            } else {
                long uploaded = res.getReceived();

                long start = uploaded;
                long end = start + CcConstant.CHUNK_SIZE - 1;
                end = end > videoInfo.getSize() - 1 ? videoInfo.getSize() - 1 : end;
                int chunkSize = (int) (end - start + 1);
                byte[] bytes = new byte[chunkSize];
                try {
                    access.seek(start);
                    int readed = access.read(bytes, 0, bytes.length);
                    if (readed < chunkSize) {
                        throw new IOException(String.format("%s bytes expected to read, but only readed:%s, chunkStart:%s, filesize:%s", chunkSize, readed, start, videoInfo.getSize()));
                    }
                    res = postChunk(start, end, localFile, bytes);
                    if (res == null) {
                        throw new IOException(String.format("Post file chunk error, no res returned, start:%s, end:%s", start, end));
                    }
                } catch (IOException e) {
                    log.info(String.format("Read local file error, start=%s,len=%s,ccvid=%s,path:%s", start, chunkSize, videoInfo.getCcvid(), videoInfo.getPath()));
                    e.printStackTrace();
                    retryCount--;
                    res = doCheck();
                }
            }

        }
        return false;
    }

    /**
     * 调用servlet/uploadmeta/v2接口,查询文件上传状态及断点位置<br><br>
     * 响应格式如下(已格式化显示):
     * <br>
     * <code>
     * {<br>
     * "result": code,<br>
     * "msg": "message“,<br>
     * "received": size<br>
     * }<br>
     * <br>
     * 例如:<br><br>
     * <p>
     * {<br>
     * "result": 0,<br>
     * "msg": "OK",<br>
     * "received": 1048576<br>
     * }<br>
     * </code>
     * <br>
     * 响应状态码result: (1, 0, -1, -2, -3) <br>
     * 1 - 文件已全部接收，上传成功<br>
     * 0 - 文件仍在上传状态中，成功返回“断点位置”<br>
     * -1 - 上传失败，可以放弃"本次"上传,不要重试了<br>
     * -2 - 服务器内部错误, 详见msg信息, 可以续传重试<br>
     * -3 - 请求参数错误,详见msg信息, 请修正错误后重试<br>
     * <p>
     * 注意：在调用servlet/uploadchunk/v2上传文件内容之前, 必须先调用一次本接口以查询
     * 该条文件的上传状态及断点位置；如果返回结果为null或者响应result=-2, 应该进行重试，
     * 以提高上传成功率
     *
     * @return json格式响应结果的封装实例Res, 没有响应结果或者响应格式有误返回null
     */
    private UploadResponse checkRange() {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(videoInfo.getMetaurl()).openConnection();
            conn.setConnectTimeout(30 * 1000);
            conn.setReadTimeout(30 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setRequestProperty("accept", "text/plain; charset=utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4)");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.connect();
            int code = conn.getResponseCode();
            if (200 != code) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            conn.disconnect();
            String result = sb.toString();
            log.info("check response: " + result);
            Gson gson = new Gson();
            UploadResponse res = gson.fromJson(result, UploadResponse.class);
            return res;
        } catch (JsonParseException je) {
            je.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    /**
     * 调用servlet/uploadchunk/v2接口, 上传文件内容块<br><br>
     * 响应格式如下(已格式化显示):
     * <br>
     * <code>
     * {<br>
     * "result": code,<br>
     * "msg": "message“,<br>
     * "received": size<br>
     * }<br>
     * <br>
     * 例如:<br><br>
     * <p>
     * {<br>
     * "result": 0,<br>
     * "msg": "OK",<br>
     * "received": 1048576<br>
     * }<br>
     * </code>
     * <br>
     * 响应状态码result: (1, 0, -1, -2, -3) <br>
     * 1 - 文件已全部接收，上传成功<br>
     * 0 - 成功接收文件块，并返回“断点位置”<br>
     * -1 - 上传失败，可以放弃"本次"上传,不要重试了<br>
     * -2 - 服务器内部错误, 详见msg信息, 可以续传重试<br>
     * -3 - 请求参数错误,详见msg信息, 请修正错误后重试<br>
     * <p>
     * 注意：如果返回结果为null或者响应result=-2, 应该再次调用servlet/uploadmeta/v2接口，
     * 查询该文件当前的上传状态及"断点位置"，并在"断点位置"的基础上重新分块上传. 可以实现适当的
     * 重试逻辑，循环"续传",以提高上传成功率.
     *
     * @param chunkStart 上传文件块的开始位置(在整个文件中的索引,inclusive, 0 <= chunkStart <= file.length()-1 )
     * @param chunkEnd   上传文件块的结束位置(在整个文件中的索引,inclusive, 0 <= chunkEnd <= file.length()-1 )
     * @param file       本地待上传文件的File实例
     * @param bufferOut  本次要发送的文件块字节数组
     * @return json格式响应结果的封装实例Res, 没有响应结果或者响应格式有误返回null
     */
    public UploadResponse postChunk(long chunkStart, long chunkEnd, File file, byte[] bufferOut) {
        HttpURLConnection conn = null;
        try {
            // 定义数据分隔线boundary
            String BOUNDARY = "---------CCHTTPAPIFormBoundaryEEXX" + new Random().nextInt(65536);
            URL openUrl = new URL(videoInfo.getChunkurl());
            conn = (HttpURLConnection) openUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4)");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            // content-range
            conn.setRequestProperty("Content-Range", "bytes " + chunkStart + "-" + chunkEnd + "/" + videoInfo.getSize());

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            StringBuilder sb = new StringBuilder();
            sb.append("--").append(BOUNDARY).append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + videoInfo.getName() + "\"\r\n");
            sb.append("Content-Type: application/octet-stream\r\n");
            sb.append("\r\n");
            byte[] data = sb.toString().getBytes();
            out.write(data);
            out.write(bufferOut);
            out.write("\r\n".getBytes());
            // 定义最后数据分隔线
            byte[] end_data = ("--" + BOUNDARY + "--\r\n").getBytes();
            out.write(end_data);
            out.flush();
            out.close();

            int code = conn.getResponseCode();
            if (200 != code) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resultBuf = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                resultBuf.append(line);
            }
            reader.close();
            conn.disconnect();

            String result = resultBuf.toString();
            log.info("resume response: " + result);
            Gson gson = new Gson();
            UploadResponse res = gson.fromJson(result, UploadResponse.class);
            return res;
        } catch (JsonParseException je) {
            je.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }


    /*public static void main(String[] args) {
        File file = new File("/tmp/test/test2.mp4");
        VideoInfo videoInfo = new VideoInfo(file);
        Uploader uploader = new Uploader(videoInfo);
        uploader.upload();
    }*/

}
