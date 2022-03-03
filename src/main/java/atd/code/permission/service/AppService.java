package atd.code.permission.service;

import atd.code.permission.entity.App;
import com.alibaba.fastjson.JSONObject;

import java.net.UnknownHostException;

public interface AppService {
    // 新增应用
    JSONObject create(App app);

    // 分页应用列表
    JSONObject select(JSONObject requestJson) throws UnknownHostException;

    // 删除应用
    JSONObject delete(Integer id);
}
