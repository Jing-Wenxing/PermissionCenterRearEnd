package atd.code.permission.controller;

import atd.code.permission.response.CommonJsonException;
import atd.code.permission.response.CommonUtil;
import atd.code.permission.response.ErrorEnum;
import atd.code.permission.service.UserService;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // 登录验证
    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    public JSONObject isLogin() {
        if(StpUtil.isLogin()) {
            return userService.isLogin(StpUtil.getLoginIdAsString());
        }
        else {
            return CommonUtil.failJson("请登陆后再进行操作吧");
        }
    }

    // 获取用户分页列表
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public JSONObject select(@RequestBody JSONObject requestJson) {
        return userService.select(requestJson);
    }

    // 获取角色列表
    @RequestMapping(value = "/rolelist", method = RequestMethod.POST)
    public JSONObject roleList(@RequestBody JSONObject requestJson) {
        return userService.roleList(requestJson.getInteger("level"));
    }

    // 修改用户角色
    @SaCheckPermission("permission-user-update")
    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
    public JSONObject updateUserRole(@RequestBody JSONObject requestJson){
        String uuid = requestJson.getString("uuid");
        String role = requestJson.getString("role");

        if (!StringUtils.hasLength(uuid) ||
                !StringUtils.hasLength(role)) {
            throw new CommonJsonException(ErrorEnum.E_506001);
        }

        return userService.updateUserRole(uuid, role);
    }
}
