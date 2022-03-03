 package atd.code.permission.controller;

import atd.code.permission.entity.Role;
import atd.code.permission.response.CommonJsonException;
import atd.code.permission.response.CommonUtil;
import atd.code.permission.response.ErrorEnum;
import atd.code.permission.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    // 新增角色
    @SaCheckPermission("permission-role-create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JSONObject create(@RequestBody JSONObject requestJson) {
        String name = requestJson.getString("name");
        String role  = requestJson.getString("role");
        String description = requestJson.getString("description");
        Integer level = requestJson.getInteger("level");

        if (!StringUtils.hasLength(name) ||
                !StringUtils.hasLength(role) ||
                !StringUtils.hasLength(description) ||
                !StringUtils.hasLength(requestJson.getString("level"))) {
            throw new CommonJsonException(ErrorEnum.E_506001);
        }

        Role roles = new Role();
        roles.setName(name);
        roles.setRole(role);
        roles.setDescription(description);
        roles.setLevel(level);

        return roleService.create(roles);
    }

    // 分页角色列表
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public JSONObject select(@RequestBody JSONObject requestJson) {
        return roleService.select(requestJson);
    }

    // 删除角色
    @SaCheckPermission("permission-role-delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody JSONObject requestJson) {
         return roleService.delete(requestJson.getInteger("id"));
    }
}