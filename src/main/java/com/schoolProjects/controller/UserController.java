package com.schoolProjects.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.schoolProjects.entity.Result;
import com.schoolProjects.entity.User;
import com.schoolProjects.mapper.UserDao;
import com.schoolProjects.service.UserService;
import com.schoolProjects.utils.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private IdWorker idWorker = new IdWorker(1, 1, 1);

    @Resource
    private UserService userService;

    Result result = new Result();


    @CrossOrigin
    @RequestMapping("/isLogin")
    private Result isLogin(HttpServletRequest request) {
        User user = (User) SessionController.getSession(request,"user");
        if (user != null && !"".equals(user.toString())) {
            result.setCode(1);
            result.setMsg("成功");
            result.setData(user);
            result.setCount(0);
            return result;
        } else {
            result.setCode(-9999);
            result.setMsg("失败");
            result.setData("");
            result.setCount(0);
            return result;
        }
    }

    @CrossOrigin
    @RequestMapping("/quit")
    private Result quit(HttpServletRequest request) {
        SessionController.removeAttribute(request, "user");
        result.setCode(1);
        result.setMsg("成功");
        result.setData("");
        result.setCount(0);
        return result;
    }


    /**
     * 根据条件查询用户
     *
     * @param request
     * @return Result
     */
    @CrossOrigin
    @RequestMapping("/selectUserBy")
    private Result selectUser(HttpServletRequest request, @RequestParam(required = false, defaultValue = "")String userId,
                              @RequestParam(required = false, defaultValue = "")String userName,@RequestParam(required = false, defaultValue = "")String phone,
                              @RequestParam(required = false, defaultValue = "")String gender,@RequestParam(required = false, defaultValue = "")String mailbox) {
        User user=new User();
        if(!"".equals(userId)){
            user.setUserId(userId);
        }
        if(!"".equals(userName)){
            user.setUserName(userName);
        }
        if(!"".equals(phone)){
            user.setPhone(phone);
        }
        if(!"".equals(gender)){
            user.setGender(gender);
        }
        if(!"".equals(mailbox)){
            user.setMailbox(mailbox);
        }
        List<User> pushInfoList = userService.selectUserBy(user);
        if (pushInfoList.size() >= 0) {
            result.setCode(1);
            result.setMsg("成功");
            result.setData(pushInfoList);
            result.setCount(0);
        } else {
            result.setCode(1);
            result.setMsg("失败");
            result.setData("");
            result.setCount(0);
        }
        return result;
    }

    /**
     * 用户登陆Web
     *
     * @param request
     * @param userId
     * @param password
     * @return
     */
    @CrossOrigin
    @RequestMapping("/login")
    private Result Login(HttpServletRequest request, String userId, String password) {
        if (userId != null && !"".equals(userId.toString()) && password != null && !"".equals(password)) {
            User user = new User();
            //邮箱正则表达式
            String patternMailbox="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
            //电话号码正则表达式
            String patternPhone="^[1][3,4,5,7,8][0-9]{9}$";
            if(Pattern.matches(patternMailbox,userId)){
                user.setMailbox(userId);
            }else if(Pattern.matches(patternPhone,userId)){
                user.setPhone(userId);
            }else{
                user.setUserId(userId);
            }
            user.setPassword(MD5Utils.UseMD5(password));
            List<User> userList = userService.login(user);
            if (userList.size() > 0) {
                result.setCode(Result.RESULT_OK);
                result.setMsg("成功");
                request.getSession().setAttribute("user", userList.get(0));
                result.setData(userList.get(0));
                result.setCount(1);
            } else {
                result.setCode(Result.RESULT_ERROR);
                result.setMsg("失败");
                result.setData("");
                result.setCount(0);
            }
            return result;
        } else {
            result.setCode(Result.RESULT_ERROR);
            result.setMsg("失败");
            result.setData("");
            result.setCount(0);
            return result;
        }
    }


    @CrossOrigin
    @RequestMapping("addUserByPhone")
    public Result addUserByPhone(HttpServletRequest request, String phone, String nickName,String password) {
        User user = new User();
        user.setPhone(phone);
        List<User> list = userService.selectUserBy(user);
        if (list.size() > 0) {
            result.setData("");
            result.setCount(0);
            result.setCode(Result.RESULT_ERROR);
            result.setMsg("用户注册失败,该电话号码已被使用");
            return result;
        }
        user.setUserId(idWorker.nextId()+"");
        user.setUserName(phone);
        user.setPhone(phone);
        user.setNickName(nickName);
        user.setAvatar("");
        user.setGender("1");
        user.setMailbox("");
        user.setRemake("");
        user.setPassword(MD5Utils.UseMD5(password));
        user.setCreatedDate(TimeUtil.getNowDetailedTime());
        try {
            userService.addUser(user);
            request.getSession().setAttribute("user", user);
            result.setData("success");
            result.setCount(1);
            result.setCode(Result.RESULT_OK);
            result.setMsg("用户注册成功");
            return result;
        } catch (Exception e) {
            result.setData("");
            result.setCount(0);
            result.setCode(Result.RESULT_ERROR);
            result.setMsg("用户注册失败");
            return result;
        }
    }

    /**
     * 修改头像
     *
     * @param request
     * @return Result
     * @throws Exception
     */
    @CrossOrigin
    @RequestMapping("multipartAction")
    public Result multipartAction(MultipartFile file, HttpServletRequest request) throws Exception {
        User user = (User) SessionController.getSession(request,"user");
        if(user==null){
            return result;
        }
        result=UploadUtil.fileSave(file,request,user.getUserId(),user.getUserId());
        Map<String, String> map= (Map<String, String>) result.getData();
        user.setAvatar(map.get("imgName"));
        userService.updateUser(user);
        return result;
    }
}
