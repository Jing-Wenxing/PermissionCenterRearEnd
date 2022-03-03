package atd.code.permission.service;

import atd.code.permission.entity.Authority;
import com.alibaba.fastjson.JSONObject;

public interface PermService {
    // 获取功能应用列表
    JSONObject getAppFunList(Integer roleId);

    // 新增授权
    JSONObject create(Authority authority);

    JSONObject delete(String id);
}
