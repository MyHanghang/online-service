package online.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

public class UploadFile {
    /**
     * @param file
     */

    public static String uploadPath(MultipartFile file,String path) {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        String prefix = "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        File dst = null;
        String returnPath = "";
        try {
            // 获取tomcat根路径
            // 创建一个指向tomcat/webapps/upload目录的对象
            File uploadDir = new File(path, "/upload/");
            System.out.println(uploadDir);
            // 如果不存在则创建upload目录
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            // 创建一个指向upload目录下的文件对象，文件名随机生成
            dst = new File(uploadDir, NoUtils.getNo() + prefix);
            // 创建文件并将上传文件复制过去
            file.transferTo(dst);
            returnPath = dst.getAbsolutePath();
            System.out.println("文件存放路径1:" + dst.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnPath;

    }
}
