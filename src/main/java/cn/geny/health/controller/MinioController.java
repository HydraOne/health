package cn.geny.health.controller;


import cn.geny.health.common.AjaxResult;
import cn.geny.health.common.RedisCache;
import cn.geny.health.po.MediaFile;
import cn.geny.health.service.impl.FileService;
import cn.geny.health.utils.MinIoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/22 10:28
 */
@RestController
@RequestMapping("/demo")
public class MinioController {
    @Autowired
    MinIoUtil minIoUtil;

    @Autowired
    FileService fileService;

    @Autowired
    RedisCache redisCache;

    @PostMapping("/upload")
    public AjaxResult uploadAvatar(String bucketName, MultipartFile file) {
        MediaFile mediaFile = new MediaFile();
        String name = file.getOriginalFilename();
        String contentType = file.getContentType();
        assert contentType != null;
        String fileExtension = contentType.split("/")[1];
        mediaFile.setName(name);
        fileService.save(mediaFile);
        String fileNum = mediaFile.getId();
        minIoUtil.upload(file,bucketName,fileNum +'.'+ fileExtension);
        return AjaxResult.success();
    }

    @GetMapping("/download")
    public void downloadFromMinio(String bucketName, String filename, HttpServletResponse resp) {
        minIoUtil.download(bucketName,filename,resp);
    }
}
