package com.schoolProjects.controller;

import com.schoolProjects.entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/2 22:51
 */
@RestController
@RequestMapping("/session")
public class SessionController {
    Result result = new Result();

    @CrossOrigin
    @RequestMapping("/session")
    private Result session(HttpServletRequest request, String sessionKey) {
        Object sessionObj = request.getSession().getAttribute(sessionKey);
        if (sessionObj != null && !"".equals(sessionObj.toString())) {
            result.setCode(1);
            result.setMsg("成功");
            result.setData(sessionObj);
            result.setCount(1);
            return result;
        } else {
            result.setCode(-9999);
            result.setMsg("失败");
            result.setData("");
            result.setCount(0);
            return result;
        }
    }

    /**
     * 获取sessoin中的多个键值对的值
     * @param request
     * @param key
     * @return
     */
    public static Map<String, Object> getSessions(HttpServletRequest request, List<String> key) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        for (int i = 0; i < key.size(); i++) {
            Object object = session.getAttribute(key.get(i));
            if (object != null) {
                map.put(key.get(i), object);
            }
        }
        return map;
    }

    /**
     * 获取session中的一个键值对的值
     * @param request
     * @param key
     * @return
     */
    public static Object getSession(HttpServletRequest request, String key) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Object object = session.getAttribute(key);
        if (object != null) {
            return object;
        }
        return null;
    }

    public static void removeAttribute(HttpServletRequest request, String key){
        request.getSession().removeAttribute(key);
    }
}
