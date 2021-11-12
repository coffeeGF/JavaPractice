package com.hwj.servlet;

import com.hwj.bean.Education;
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
@WebServlet("/education/*")
public class EducationServlet extends BaseServlet {
    public void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Education> list = service.list();
        request.setAttribute("educations",list);
        //转发
        request.getRequestDispatcher("/WEB-INF/page/admin/education.jsp").forward(request,response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Education education = new Education();
        BeanUtils.populate(education,request.getParameterMap());
        boolean flag = service.save(education);
        if (flag) {
            //重定向到admin
            response.sendRedirect(request.getContextPath()+"/education/admin");
        }else {
            request.setAttribute("error","教育信息保存失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request,response);
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        boolean flag = service.remove(id);
        if (flag) {
            //重定向到admin
            redirect(request,response,"education/admin");
        }else {
            forwardError(request,response,"教育信息保存失败");
        }
    }
}
