package com.hui.tupian.controller;

import com.hui.tupian.service.GetData;
import com.hui.tupian.util.Result;
import com.hui.tupian.util.SessionUser;
import com.hui.tupian.util.SessionUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 查询数据后端
 */
@Controller
@RequestMapping("goindex")
public class IndexController {

    /**
     * 查询数据
     *
     * @param url
     * @param time
     * @param request
     * @return
     */
    @GetMapping("doQuZhen")
    public Result<?> doQuZhen(String url, Integer time, HttpServletRequest request) {
        SessionUser sessionUser = SessionUtil.getSessionUser(request);
        List<JSONObject> data = GetData.getData();
        return Result.OK(data);
    }


}
