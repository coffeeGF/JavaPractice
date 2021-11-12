package com.hwj.servlet;

import com.hwj.bean.Website;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:44 下午
 */
@WebServlet("/website/*")
public class WebsiteServlet extends BaseServlet {
    public void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Website> list = service.list();
        Website website = null;
        if (list.size() > 0) {
            website = list.get(0);
        }
        request.setAttribute("website",website);
        //转发
        forward(request,response,"admin/website.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Website website = new Website();
        BeanUtils.populate(website,request.getParameterMap());
        boolean flag = service.save(website);
        if (flag) {
            //重定向到admin
            redirect(request,response,"website/admin");
        }else {
            forwardError(request,response,"网站信息保存失败");
        }
    }

}
