package atd.code.permission.service;

import atd.code.permission.entity.Function;
import com.alibaba.fastjson.JSONObject;

public interface FuncService {
    // 新增功能
    JSONObject create(Function function);

    // 分页功能列表
    JSONObject select(JSONObject requestJson);

    // 删除功能
    JSONObject delete(Integer id);
}
