package com.hwj.servlet;
import com.hwj.bean.Award;
import com.hwj.bean.UploadParams;
import com.hwj.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author houwenjing
 * @description
 * @date 2021/8/24 10:52 上午
 */
@WebServlet("/award/*")
public class AwardServlet extends BaseServlet <Award> {
    public void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Award> list = service.list();
        request.setAttribute("awards",list);
        //转发
        forward(request,response,"admin/award.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadParams uploadParams = Uploads.parseRequest(request);
        Award award = new Award();
        BeanUtils.populate(award,uploadParams.getParams());
        FileItem fileItem = uploadParams.getFileParams().get("imageFile");
        String newFileName = Uploads.uploadImage(fileItem,request);
        if (newFileName != null) {
            award.setImage(newFileName);
        }
        //容错，数据库不存储空字符串
        if (award.getImage() == null || award.getImage().length() <= 0) {
            award.setImage(null);
        }
        boolean flag = service.save(award);
        if (flag) {
            //重定向到admin
            redirect(request, response, "award/admin");
        }else {
            forwardError(request, response, "获奖信息保存失败");
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] idStrs = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Integer.valueOf(idStr));
        }
        // 删除
        if (service.remove(ids)) {
            redirect(request, response, "award/admin");
        } else {
            forwardError(request, response, "获奖成就删除失败");
        }
    }
}
