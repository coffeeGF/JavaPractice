package com.hwj.util;

import com.hwj.bean.UploadParams;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author houwenjing
 * @description
 * @date 2021/8/24 3:20 下午
 */
public class Uploads {
    private static String BASE_DIR = "upload";
    private static String IMG_DIR = "img";

    /**
     * @param item    文件参数
     * @param request servlet请求
     * @return 文件路径
     * @throws IOException
     */
    public static String uploadImage(FileItem item, HttpServletRequest request) throws IOException {
        if (item == null) return null;
        InputStream is = item.getInputStream();
        if (is.available() <= 0) return null;
        String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(item.getName());
        String image = BASE_DIR + "/" + IMG_DIR + "/" + fileName;
        String filePath = request.getServletContext().getRealPath(image);
        FileUtils.copyInputStreamToFile(is, new File(filePath));
        return image;
    }

    public static UploadParams parseRequest(HttpServletRequest request) throws Exception {
        Map<String, FileItem> fileParams = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            String fieldName = item.getFieldName();
            if (item.isFormField()) {
                params.put(fieldName, item.getString("UTF-8"));
            } else {
                fileParams.put(fieldName, item);
            }
        }
        UploadParams uploadParams = new UploadParams();
        uploadParams.setParams(params);
        uploadParams.setFileParams(fileParams);
        return uploadParams;
    }
}
