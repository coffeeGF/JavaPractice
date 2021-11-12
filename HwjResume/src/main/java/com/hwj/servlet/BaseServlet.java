package com.hwj.servlet;

import com.hwj.service.BaseService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:42 下午
 */
@WebServlet(name = "BaseServlet")
public abstract class BaseServlet<T> extends HttpServlet {
    static {
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPatterns(new String[]{"yyyy-MM-dd"});
        ConvertUtils.register(dateConverter, Date.class);
    }

    protected BaseService<T> service = newService();

    protected BaseService<T> newService() {
        // com.mj.xr.servlet.WebsiteServlet
        // com.mj.xr.service.impl.WebsiteServiceImpl
        try {
            String clsName = getClass().getName()
                    .replace(".servlet.", ".service.impl.")
                    .replace("Servlet", "ServiceImpl");
            return (BaseService<T>) Class.forName(clsName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            String[] cmps = uri.split("/");
            String methodName = cmps[cmps.length - 1];
            //Uris.lastPath(request.getRequestURI()
            Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            Throwable exception = e;
            while (exception.getCause() != null) {
                exception = exception.getCause();
            }
            request.setAttribute("error", exception.getClass().getSimpleName() + exception.getMessage());
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
//            e.printStackTrace();
        }
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + "/" + path);
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/page/" + path).forward(request, response);
    }

    protected void forwardError(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
        request.setAttribute("error", error);
        forward(request, response, "error.jsp");
    }

}
