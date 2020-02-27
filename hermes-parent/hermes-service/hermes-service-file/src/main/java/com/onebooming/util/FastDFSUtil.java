package com.onebooming.util;

import com.onebooming.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-09 11:12
 * @ApiNote：实现FastDFS的文件管理
 *          文件上传
 *          文件下载
 *          文件删除
 *          文件信息获取
 *          storage信息获取
 *          traker信息获取
 */
public class FastDFSUtil {
    /**
     * 加载Tracker
     */
    static {
        //从classpath下获取文件对象获取路径
        String path = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param fastDFSFile
     * @return
     * @throws Exception
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        //附加参数
        //获取文件作者
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] =new NameValuePair("author",fastDFSFile.getAuthor());
        /***
         * 文件上传后的返回值
         * uploadResults[0]:文件上传所存储的组名，例如:group1
         * uploadResults[1]:文件存储路径,例如：M00/00/00/wKjThF0DBzaAP23MAAXz2mMp9oM26.jpeg
         */
        String[] uploadResults = null;

        //获取StorageClient对象
        StorageClient storageClient = getStorageClient();
        /**
         * 通过StorageClient访问Storage，实现文件上传，并且获取文件上传后的存储信息
         * 参数1：上传文件的字节数组
         * 参数2：文件的扩展名 .jpg
         * 参数3：附加参数，比如拍摄地址：北京；作者等
         */
        //执行文件上传
         uploadResults = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
         return uploadResults;
    }

    /**
     * 获取文件信息
     * @param groupName：组名
     * @param remoteFileName：文件存储完整名
     * @return
     * @throws Exception
     */
    public static FileInfo getFile(String groupName,String remoteFileName) throws Exception {
        //获取StorageClient对象
        StorageClient storageClient = getStorageClient();
        //获取文件信息
        /**
         * fileInfo.getSource_ip_addr()...等信息都可以取得
         */
        return storageClient.get_file_info(groupName,remoteFileName);
    }

    /**
     * 文件下载
     * @param groupName：组名
     * @param remoteFileName：文件存储完整名
     * @return
     * @throws Exception
     */
    public static InputStream download(String groupName, String remoteFileName) throws Exception{
        //获取StorageClient对象
        StorageClient storageClient = getStorageClient();
        //通过StorageClient下载文件
        byte[] downloadFile = storageClient.download_file(groupName, remoteFileName);
        //将字节数组转化为字节输入流
        return new ByteArrayInputStream(downloadFile);
    }

    /**
     * 文件删除
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception{
        //获取StorageClient对象
        StorageClient storageClient = getStorageClient();
        //通过StorageClient删除文件
        storageClient.delete_file(groupName,remoteFileName);
    }

    /**
     * 获取组信息
     * @param groupName
     * @return
     * @throws Exception
     */
    public static StorageServer getStorages(String groupName) throws Exception{
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过trackerClient获取Storage组信息
        return trackerClient.getStoreStorage(trackerServer,groupName);
    }

    /***
     * 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     * @param groupName :组名
     * @param remoteFileName ：文件存储完整名
     * @return
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws Exception{
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取服务信息
        return trackerClient.getFetchStorages(trackerServer,groupName,remoteFileName);
    }
    /***
     * 获取Tracker服务地址
     */
    public static String getTrackerUrl(){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Tracker地址
            return "http://"+trackerServer.getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上面所有方法中都会涉及到获取TrackerServer或者StorageClient，
     * 我们可以把它们单独抽取出去，分别在类中添加如下2个方法
     */

    /**
     * 获取TrackerServer
     * @return
     * @throws Exception
     */
    public static TrackerServer getTrackerServer() throws Exception{
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer对象
        //通过TrackerClient访问TrackerService服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //返回TrackerServer对象
        return trackerServer;
    }

    /**
     * 获取StorageClient
     * @return
     * @throws Exception
     */
    public static StorageClient getStorageClient() throws Exception{
        //获取TrackerServer
        TrackerServer trackerServer = getTrackerServer();
        //通过TrackerServer创建StorageClient对象
        //通过TrackerServer的链接信息可以获取Storage的链接信息，创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //返回对象
        return storageClient;
    }

    public static void main(String[] args) throws Exception {
//        //测试下载
//        InputStream is = download("group1", "M00/00/00/rBAKyF4_-46ASEfGAACWyxhOGLM262.jpg");
//        //将问价写入磁盘
//        FileOutputStream fos = new FileOutputStream("/Users/chenglei/Desktop/download_fastdfs.jpg");
//        //定义一个缓冲区
//        byte[] buffer = new byte[1024];
//        while(is.read(buffer)!=-1){
//            fos.write(buffer);
//        }
//        fos.flush();
//        fos.close();
//        is.close();
//        System.out.println("文件下载完成");

        //获取Storage信息
        StorageServer storageServer = getStorages("group1");
        System.out.println(storageServer.getStorePathIndex());
        System.out.println(storageServer.getInetSocketAddress().getHostString());//获取IP
    }
}
