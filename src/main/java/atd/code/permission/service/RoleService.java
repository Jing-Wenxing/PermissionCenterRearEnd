package atd.code.permission.service;

import atd.code.permission.entity.Role;
import com.alibaba.fastjson.JSONObject;

public interface RoleService {
    // 新增角色
    JSONObject create(Role role);

    // 分页角色列表
    JSONObject select(JSONObject requestJson);

    // 删除角色
    JSONObject delete(Integer id);
}
