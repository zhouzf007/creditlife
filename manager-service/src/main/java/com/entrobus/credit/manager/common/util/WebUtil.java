
package com.entrobus.credit.manager.common.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

/**
 * web相关工具类
 */
public final class WebUtil {

    /**
     * 获取所有request请求参数key-value
     * 
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {

        Map<String, String> params = Maps.newHashMap();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }

    /**
     * 获取所有request请求参数key-value，过滤掉空值
     *
     * @param request
     * @return
     */
    public static Map<String, String> getNotBlankRequestParams(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMap();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                if (StringUtils.isNotBlank(request.getParameter(key))) {
                    params.put(key, request.getParameter(key));
                }
            }
        }
        return params;
    }

    /**
     * 获得客户端IP
     * 
     * @param request
     * @return
     */
    public static String getRealIp(HttpServletRequest request) {
        //如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串Ｉｐ值
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getRealIpV2(HttpServletRequest request) {
        String accessIP = request.getHeader("x-forwarded-for");
        if (null == accessIP)
            return request.getRemoteAddr();
        return accessIP;
    }

    public static String urlEncode(String source) {
        String res = source;
        try {
            res = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * 判断是否是ajax请求
     * 
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String flag = request.getHeader("Request-Flag");
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            return true;
        }
        return false;
    }

    /**
     * 获得带参数的URL
     *
     * @param request
     * @return
     */
    public static String getUriWidthParam(HttpServletRequest request) {
        String url = request.getRequestURI();
        String method = request.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            Map<String, String> params = getRequestParams(request);
            String join = Joiner.on("&").withKeyValueSeparator("=").join(params);
            return URLTool.builderURL(url, join);
        }
        return url;
    }

    /**
     * 获取当前请求对象
     * 
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * 写出数据
     *
     * @param res 输出的字符串
     * @throws Exception
     */
    public static void write(String res, HttpServletResponse response) {
        Writer writer = null;
        try {
            res = (null == res ? "" : res);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.write(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if(inet!=null){
                    ipAddress= inet.getHostAddress();
                }
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
