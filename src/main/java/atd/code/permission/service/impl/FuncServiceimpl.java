package atd.code.permission.service.impl;

import atd.code.permission.entity.App;
import atd.code.permission.entity.Function;
import atd.code.permission.mapper.FuncMapper;
import atd.code.permission.response.CommonJsonException;
import atd.code.permission.response.CommonUtil;
import atd.code.permission.response.ErrorEnum;
import atd.code.permission.service.FuncService;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncServiceimpl implements FuncService {
    @Autowired
    FuncMapper funcMapper;

    // 新增功能
    @Override
    public JSONObject create(Function function) {
        if (funcMapper.create(function) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }

    // 分页功能列表
    @Override
    public JSONObject select(JSONObject requestJson){
        JSONObject result = new JSONObject();
        Integer index = (requestJson.getInteger("current") - 1) * requestJson.getInteger("pageSize");
        Integer appid = requestJson.getInteger("appid");

        // 权限判断
        if(!StpUtil.hasPermission("permission-app-set"+funcMapper.getAppInfo(appid).getPermission())) {
            throw new CommonJsonException(ErrorEnum.E_501002);
        }

        result.put("current", requestJson.getInteger("current"));
        result.put("pageSize", requestJson.getInteger("pageSize"));
        result.put("length", funcMapper.selectLength(appid));
        result.put("list", funcMapper.selectInfo(index, requestJson.getInteger("pageSize"), appid));

        return CommonUtil.successJson(result);
    }

    // 删除功能
    @Override
    public JSONObject delete(Integer id) {
        if (funcMapper.delete(id) == 1) {
            funcMapper.deletePermOfFunc(id);
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }
}
