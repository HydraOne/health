package cn.geny.health.service.impl;

import cn.geny.health.mapper.FileMapper;
import cn.geny.health.po.MediaFile;
import cn.geny.health.utils.MinIoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO
 * @author wangjiahao
 * @date 2022/4/3 23:46
 */
@Service
public class FileService extends ServiceImpl<FileMapper, MediaFile> {
    @Autowired
    MinIoUtil minIoUtil;

    public MediaFile uploadMedia(MultipartFile multipartFile){
        MediaFile mediaFile = new MediaFile();
        String name = multipartFile.getOriginalFilename();
        mediaFile.setName(name);
        this.save(mediaFile);
        String fileNum = mediaFile.getId();
        if(!minIoUtil.upload(multipartFile,"demo",fileNum)){
            this.removeById(fileNum);
            throw new RuntimeException("文件上传失败");
        }
        return mediaFile;
    }
}