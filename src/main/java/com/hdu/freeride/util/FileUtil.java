package com.hdu.freeride.util;

import com.hdu.freeride.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/25 20:28
 */
public class FileUtil {

    public static String upload(MultipartFile file, String filePath) {

        String fileName = "";
        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
            File fileSourcePath  = new File(filePath);
            if (!fileSourcePath .exists()) {
                fileSourcePath .mkdirs();
            }
            try {
                File fileSource = new File(filePath, fileName);
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(fileSource));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
                throw new MyException(e.getMessage());
            }
        } else {
            throw new MyException("上传失败，因为文件是空的");
        }
        return filePath + fileName;
    }
}
