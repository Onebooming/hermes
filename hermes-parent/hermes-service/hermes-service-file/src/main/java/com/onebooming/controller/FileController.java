package com.onebooming.controller;
import com.onebooming.file.FastDFSFile;
import com.onebooming.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-09 14:57
 * @ApiNote
 */
@RestController
@RequestMapping(value = "/file")
@CrossOrigin
public class FileController {

    /***
     * 文件上传
     * @return
     */
    @PostMapping(value = "/upload")
    public Result<String> upload(@RequestParam("file")MultipartFile file) throws Exception {
        //封装一个FastDFSFile
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), //文件名字
                file.getBytes(),            //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名

        //文件上传
        String[] uploads = FastDFSUtil.upload(fastDFSFile);
        //拼接文件上传地址
        String url = FastDFSUtil.getTrackerUrl()+"/"+uploads[0]+"/"+uploads[1];
        return new Result<>(true, StatusCode.OK,"文件上传成功",url);
    }
}