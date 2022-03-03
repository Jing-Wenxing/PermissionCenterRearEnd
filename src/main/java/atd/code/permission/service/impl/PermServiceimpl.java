package atd.code.permission.service.impl;

import atd.code.permission.entity.App;
import atd.code.permission.entity.Authority;
import atd.code.permission.entity.Function;
import atd.code.permission.entity.Role;
import atd.code.permission.mapper.PermMapper;
import atd.code.permission.response.CommonUtil;
import atd.code.permission.response.ErrorEnum;
import atd.code.permission.service.PermService;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermServiceimpl implements PermService {
    @Autowired
    PermMapper permMapper;

    // 获取功能应用列表
    @Override
    public JSONObject getAppFunList(Integer roleId) {
        JSONArray result = new JSONArray();

        Role loginUser = permMapper.getRoleInfo(permMapper.getUserInfo(StpUtil.getLoginIdAsString()).getRoleid());
        Role postUser = permMapper.getRoleInfo(roleId);

        // 自行授权权限判断
        if(!StpUtil.hasPermission("permission-role-self") && loginUser.getLevel().equals(postUser.getLevel())) {
            return CommonUtil.errorJson(ErrorEnum.E_501002);
        }

        // 下行授权权限判断
        if(!StpUtil.hasPermission("permission-role-down") && loginUser.getLevel() > postUser.getLevel()) {
            return CommonUtil.errorJson(ErrorEnum.E_501002);
        }

        // 下行授权权限判断
        if(loginUser.getLevel() < postUser.getLevel()) {
            return CommonUtil.errorJson(ErrorEnum.E_501003);
        }

        // 获取应用列表
        List<App> appList = permMapper.getAppList();
        for (App item : appList) {
            JSONObject appItem = new JSONObject();
            appItem.put("app_info", item);
            // 获取角色信息
            Role role = permMapper.getRoleInfo(roleId);
            // 获取功能列表
            List<Function> funcList = permMapper.getAppIdToFuncList(item.getId(), role.getLevel());
            for (Function func : funcList) {
                Authority authority = permMapper.roleHasAuthority(roleId, func.getId());
                func.setAuthorityid(authority != null ? authority.getId() : null);
                func.setUser_used(authority != null ? true : false);
            }
            appItem.put("func_list", funcList);
            result.add(appItem);
        }
        return CommonUtil.successJson(result);
    }

    // 新增授权
    @Override
    public JSONObject create(Authority authority) {
        if (permMapper.create(authority) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }

    // 新增授权
    @Override
    public JSONObject delete(String id) {
        if (permMapper.delete(id) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }
}
