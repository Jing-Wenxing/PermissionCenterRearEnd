package atd.code.permission.controller;

import atd.code.permission.entity.App;
import atd.code.permission.entity.Function;
import atd.code.permission.response.CommonJsonException;
import atd.code.permission.response.ErrorEnum;
import atd.code.permission.service.FuncService;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/func")
public class FuncController {
    @Autowired
    FuncService funcService;

    // 增加功能
    @SaCheckPermission("permission-func-create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JSONObject create(@RequestBody JSONObject requestJson) {
        String name = requestJson.getString("name");
        String permission = requestJson.getString("permission");
        String description = requestJson.getString("description");
        String appid = requestJson.getString("appid");
        String second_per = requestJson.getString("second_per");
        String level = requestJson.getString("level");

        if (!StringUtils.hasLength(name) ||
                !StringUtils.hasLength(permission) ||
                !StringUtils.hasLength(description) ||
                !StringUtils.hasLength(appid) ||
                !StringUtils.hasLength(level)) {
            throw new CommonJsonException(ErrorEnum.E_506001);
        }

        Function function = new Function();
        function.setName(name);
        function.setPermission(permission);
        function.setDescription(description);
        function.setAppid(appid);
        function.setSecond_per(second_per);
        function.setLevel(level);

        return funcService.create(function);
    }

    // 应用分页功能
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public JSONObject select(@RequestBody JSONObject requestJson) {
        return funcService.select(requestJson);
    }

    // 删除功能
    @SaCheckPermission("permission-func-delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody JSONObject requestJson) {
        return funcService.delete(requestJson.getInteger("id"));
    }
}
