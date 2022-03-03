package atd.code.permission.controller;

import atd.code.permission.entity.App;
import atd.code.permission.entity.Role;
import atd.code.permission.response.CommonJsonException;
import atd.code.permission.response.ErrorEnum;
import atd.code.permission.service.AppService;
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
@RequestMapping("/app")
public class AppController {
    @Autowired
    AppService appService;

    // 新增应用
    @SaCheckPermission("permission-app-create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JSONObject create(@RequestBody JSONObject requestJson) {
        String name = requestJson.getString("name");
        String permission = requestJson.getString("permission");
        String description = requestJson.getString("description");
        String port = requestJson.getString("port");

        if (!StringUtils.hasLength(name) ||
                !StringUtils.hasLength(permission) ||
                !StringUtils.hasLength(description) ||
                !StringUtils.hasLength(port)) {
            throw new CommonJsonException(ErrorEnum.E_506001);
        }

        App app = new App();
        app.setName(name);
        app.setPermission(permission);
        app.setDescription(description);
        app.setPort(port);

        return appService.create(app);
    }

    // 分页应用列表
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public JSONObject select(@RequestBody JSONObject requestJson) throws UnknownHostException {
        return appService.select(requestJson);
    }

    // 删除应用
    @SaCheckPermission("permission-app-delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody JSONObject requestJson) {
        return appService.delete(requestJson.getInteger("id"));
    }
}
