package atd.code.permission.controller;

import atd.code.permission.entity.Authority;
import atd.code.permission.response.CommonJsonException;
import atd.code.permission.response.ErrorEnum;
import atd.code.permission.service.PermService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perm")
public class PermController {
    @Autowired
    PermService permService;

    // 获取功能应用列表
    @RequestMapping(value = "/getAppFunList", method = RequestMethod.POST)
    public JSONObject getAppFunList(@RequestBody JSONObject requestJson) {
        String roleId = requestJson.getString("roleId");

        if (!StringUtils.hasLength(roleId)) {
            throw new CommonJsonException(ErrorEnum.E_506001);
        }

        return permService.getAppFunList(requestJson.getInteger("roleId"));
    }

    // 新增权限
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JSONObject create(@RequestBody JSONObject requestJson) {
        String roleid = requestJson.getString("roleid");
        String appid = requestJson.getString("appid");
        String funcid = requestJson.getString("funcid");
        String permission = requestJson.getString("permission");

        if (!StringUtils.hasLength(roleid) ||
                !StringUtils.hasLength(appid) ||
                !StringUtils.hasLength(funcid) ||
                !StringUtils.hasLength(permission)) {
            throw new CommonJsonException(ErrorEnum.E_506001);
        }

        Authority authority = new Authority();
        authority.setRoleid(roleid);
        authority.setAppid(appid);
        authority.setFuncid(funcid);
        authority.setPermission(permission);

        return permService.create(authority);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody JSONObject requestJson) {
        String id = requestJson.getString("id");

        if (!StringUtils.hasLength(id)) {
            throw new CommonJsonException(ErrorEnum.E_506001);
        }

        return permService.delete(id);
    }
}
