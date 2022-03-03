package atd.code.permission.service.impl;

import atd.code.permission.entity.Role;
import atd.code.permission.mapper.RoleMapper;
import atd.code.permission.response.CommonUtil;
import atd.code.permission.service.RoleService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceimpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    // 新增角色
    @Override
    public JSONObject create(Role role) {
        if (roleMapper.create(role) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }

    // 分页角色列表
    public JSONObject select(JSONObject requestJson) {
        JSONObject result = new JSONObject();
        Integer index = (requestJson.getInteger("current") - 1) * requestJson.getInteger("pageSize");

        result.put("current", requestJson.getInteger("current"));
        result.put("pageSize", requestJson.getInteger("pageSize"));
        result.put("length", roleMapper.selectLength());
        result.put("list", roleMapper.selectInfo(index, requestJson.getInteger("pageSize")));

        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject delete(Integer id) {
        if (roleMapper.delete(id) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }
}
