package top.hzfq.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 23:11
 */
public class HttpServletUtil {

    /**
     * 获取响应对象
     */
    public static HttpServletResponse getResponse() {
        return servletRequestAttributes().getResponse();
    }

    /**
     * 获取请求对象
     */
    public static HttpServletRequest getRequest() {
        return servletRequestAttributes().getRequest();
    }

    /**
     * 获取会话对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取请求属性
     */
    public static Object getRequestAttribute(String attrName) {
        return getRequest().getAttribute(attrName);
    }

    /**
     * 设置请求属性
     */
    public static void setRequestAttribute(String attrName, Object attrObj) {
        getRequest().setAttribute(attrName, attrObj);
    }

    /**
     * 获取会话属性
     */
    public static Object getSessionAttribute(String attrName) {
        return getSession().getAttribute(attrName);
    }

    /**
     * 设置会话属性
     */
    public static void setSessionAttribute(String attrName, Object attrObj) {
        getSession().setAttribute(attrName, attrObj);
    }

    /**
     * 获取请求属性对象
     */
    public static ServletRequestAttributes servletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }
}
