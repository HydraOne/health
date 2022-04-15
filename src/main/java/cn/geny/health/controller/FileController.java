package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.po.Account;
import cn.geny.health.po.MediaFile;
import cn.geny.health.service.impl.FileService;
import cn.geny.health.service.impl.UserService;
import cn.geny.health.utils.MinIoUtil;
import cn.geny.health.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/22 10:28
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    MinIoUtil minIoUtil;

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

//    @PostMapping("/uploadAvatar")
//    public void uploadToMinio(String bucketName, MultipartFile file) {
//        MediaFile mediaFile = new MediaFile();
//        SecurityUtils.getLoginUser();
//        String name = file.getOriginalFilename();
//        String contentType = file.getContentType();
//        if (StringUtils.isNotBlank(contentType)){
//            mediaFile.setName(name);
//            fileService.save(mediaFile);
//            String fileNum = mediaFile.getId();
//            minIoUtil.upload(file,"demo",fileNum);
//            String currentUserId = SecurityUtils.getUserId();
//            Account needUpdateAccount = new Account();
//            needUpdateAccount.setId(currentUserId);
//            needUpdateAccount.setIcon(fileNum);
//            userService.updateById(needUpdateAccount);
//        }
//    }

    @PostMapping("/uploadAvatar")
    public void uploadAvatarToMinio(String bucketName, MultipartFile file) {
        MediaFile mediaFile = fileService.uploadMedia(file);
        Account needUpdateAccount = new Account();
        needUpdateAccount.setId(SecurityUtils.getUserId());
        needUpdateAccount.setIcon(mediaFile.getId());
        userService.updateById(needUpdateAccount);
    }

    @PostMapping("/uploadPictures")
    public AjaxResult uploadToMinio(MultipartFile[] files) {
        List<String> list = new ArrayList<>();
        if (Objects.nonNull(files)){
            Arrays.stream(files).forEach(file-> list.add(fileService.uploadMedia(file).getId()));
        }
        return AjaxResult.success(list);
    }

    @GetMapping("/download")
    public void downloadFromMinio(String bucketName, String filename, HttpServletResponse resp) {
        minIoUtil.download(bucketName,filename,resp);
    }
}