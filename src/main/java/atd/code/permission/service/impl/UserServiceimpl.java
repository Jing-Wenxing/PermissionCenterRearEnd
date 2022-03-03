package atd.code.permission.service.impl;

import atd.code.permission.entity.Authority;
import atd.code.permission.entity.Role;
import atd.code.permission.entity.User;
import atd.code.permission.entity.UserInfo;
import atd.code.permission.mapper.UserMapper;
import atd.code.permission.response.CommonUtil;
import atd.code.permission.response.ErrorEnum;
import atd.code.permission.service.UserService;
import atd.code.permission.utils.redis.RedisUtils;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    // 个人信息
    @Override
    public JSONObject isLogin(String uuid) {
        // 获取个人信息
        UserInfo userInfo = userMapper.getUUIDToUserInfo(uuid);
        // 角色校验 - 是否为管理员
        if (userInfo.getRole().indexOf("admin") == -1) {
            return CommonUtil.errorJson(ErrorEnum.E_501001);
        }
        userInfo.setRoleInfo(userMapper.getRoleInfo(userInfo.getRole()));
        userInfo.setAuthorityInfoList(userMapper.authorityList(userInfo.getRoleInfo().getId()));

        return CommonUtil.successJson(userInfo);
    }

    // 分页角色列表
    public JSONObject select(JSONObject requestJson) {
        JSONObject result = new JSONObject();
        Integer index = (requestJson.getInteger("current") - 1) * requestJson.getInteger("pageSize");

        result.put("current", requestJson.getInteger("current"));
        result.put("pageSize", requestJson.getInteger("pageSize"));
        result.put("length", userMapper.selectLength());
        result.put("list", userMapper.selectInfo(index, requestJson.getInteger("pageSize"),
                requestJson.getInteger("level") == 6 ? 7 : requestJson.getInteger("level")));

        return CommonUtil.successJson(result);
    }

    // 获取角色列表
    @Override
    public JSONObject roleList(Integer level) {
        return CommonUtil.successJson(userMapper.roleList(level == 6 ? 7 : level));
    }

    @Override
    public JSONObject updateUserRole(String uuid, String role) {
        // 权限判断

        Role loginRole = userMapper.getRoleInfo(userMapper.getUUIDToUserInfo(StpUtil.getLoginIdAsString()).getRole());
        Role postRole = userMapper.getRoleInfo(userMapper.getUUIDToUserInfo(uuid).getRole());
        Role changeRole = userMapper.getRoleInfo(role);

        // 超级管理员直接放行
        if (!userMapper.getUUIDToUserInfo(StpUtil.getLoginIdAsString()).getRole().equals("superadmin")) {
            // 登陆人是否可以操作本用户
            if (loginRole.getLevel() <= postRole.getLevel()) {
                return CommonUtil.errorJson(ErrorEnum.E_501002);
            }
            // 登陆人是否可以操作本身份修改
            if (loginRole.getLevel() <= changeRole.getLevel()) {
                return CommonUtil.errorJson(ErrorEnum.E_501002);
            }
        }

        if (userMapper.updateUserRole(uuid, role, changeRole.getId().toString()) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }
}
