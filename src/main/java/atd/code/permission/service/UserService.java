package atd.code.permission.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {
    // 登录验证
    JSONObject isLogin(String uuid);

    JSONObject select(JSONObject requestJson);

    JSONObject roleList(Integer level);

    JSONObject updateUserRole(String uuid, String role);
}
