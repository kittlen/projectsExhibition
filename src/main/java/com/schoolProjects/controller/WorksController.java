package com.schoolProjects.controller;

import com.schoolProjects.entity.Result;
import com.schoolProjects.entity.User;
import com.schoolProjects.entity.Works;
import com.schoolProjects.entity.WorksType;
import com.schoolProjects.service.WorksService;
import com.schoolProjects.utils.IdWorker;
import com.schoolProjects.utils.MD5Utils;
import com.schoolProjects.utils.TimeUtil;
import com.schoolProjects.utils.UploadUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
@RestController
@RequestMapping("/works")
public class WorksController {

    private IdWorker idWorker = new IdWorker(1, 1, 1);

    @Resource
    private WorksService worksService;

    Result result = new Result();


    @CrossOrigin
    @RequestMapping("/selectWorksBy")
    private Result selectWorksBy(@RequestParam(required = false, defaultValue = "")String worksTitle, @RequestParam(required = false, defaultValue = "")String worksTypeId, int dateType, int pageNumber, int pageSize){
        Works works=new Works();
        works.setWorksTitle(worksTitle);
        WorksType worksType=new WorksType();
        worksType.setTypeId(worksTypeId);
        works.setWorksType(worksType);
        String dateTime="";
        switch (dateType){
            case 0://全部
                dateTime="";
                break;
            case 1://最近一个月
                dateTime=TimeUtil.getNowDetailedTime(1,1);
                break;
            case 2://最近三个月
                dateTime=TimeUtil.getNowDetailedTime(1,3);
                break;
            case 3://最近半年
                dateTime=TimeUtil.getNowDetailedTime(1,6);
                break;
            case 4://最近1年
                dateTime=TimeUtil.getNowDetailedTime(2,1);
                break;
        }
        works.setCreatedDate(dateTime);
        List<Works> list=worksService.selectWorksBy(works,pageNumber,pageSize);
        result.setData(list);
        result.setCount(list.size());
        result.setCode(Result.RESULT_OK);
        result.setMsg("查询成功");
        return result;
    }

    @CrossOrigin
    @RequestMapping("/selectWorksById")
    private Result selectWorksById(String worksId){
        List<Works> list=worksService.selectWorksById(worksId);
        if(list!=null&&list.size()>0) {
            result.setData(list);
            result.setCount(list.size());
            result.setCode(Result.RESULT_OK);
            result.setMsg("查询成功");
        }else{
            result.setData("error");
            result.setCount(0);
            result.setCode(Result.RESULT_ERROR);
            result.setMsg("查询失败");
        }
        return result;
    }


    @CrossOrigin
    @RequestMapping("/imageUpload")
    public Result imageUpload(MultipartFile file, HttpServletRequest request)  {
        User user = (User) SessionController.getSession(request,"user");
        if(user==null){
            result.setData("");
            result.setCount(0);
            result.setCode(Result.RESULT_ERROR);
            result.setMsg("发布失败");
            return result;
        }
        result= UploadUtil.fileSave(file,request,user.getUserId(),idWorker.nextId()+"");
//        Result result= UploadUtil.fileSave(file,request,"123456789");
        return result;

    }

    @CrossOrigin
    @RequestMapping("delete")
    public Result delete(HttpServletRequest request,String worksId){
        Works works=new Works();
        works.setWorksId(worksId);
        worksService.delete(works);
        result.setData("success");
        result.setCount(1);
        result.setCode(Result.RESULT_OK);
        result.setMsg("删除成功");
        return result;
    }

    @CrossOrigin
    @RequestMapping("addWorks")
    public Result addWorks(HttpServletRequest request, String worksTitle,String worksTypeId,
                           String worksIntroduction,String worksUris) {
        Works works=new Works();
        works.setWorksId(idWorker.nextId()+"");
        works.setWorksTitle(worksTitle);
        WorksType worksType=new WorksType();
        worksType.setTypeId(worksTypeId);
        User user=new User();
        user.setUserId("123");
        works.setUser(user);
        works.setWorksType(worksType);
        works.setWorksIntroduction(worksIntroduction);
        works.setWorksUris(worksUris);
        works.setWorksStyle("0");
        works.setWorksCover("");
        works.setCreatedDate(TimeUtil.getNowDetailedTime());
        try {
            worksService.addWorks(works);
            result.setData("success");
            result.setCount(1);
            result.setCode(Result.RESULT_OK);
            result.setMsg("发布成功");
            return result;
        }catch (Exception e){
            result.setData("error");
            result.setCount(0);
            result.setCode(Result.RESULT_ERROR);
            result.setMsg("发布失败");
            return result;
        }

    }

}
