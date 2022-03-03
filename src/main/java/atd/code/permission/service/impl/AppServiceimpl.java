package atd.code.permission.service.impl;

import atd.code.permission.entity.App;
import atd.code.permission.mapper.AppMapper;
import atd.code.permission.response.CommonUtil;
import atd.code.permission.service.AppService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class AppServiceimpl implements AppService {
    @Autowired
    AppMapper appMapper;

    // 新增应用
    @Override
    public JSONObject create(App app) {
        if (appMapper.create(app) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }

    // 分页应用列表
    @Override
    public JSONObject select(JSONObject requestJson) throws UnknownHostException {
        JSONObject result = new JSONObject();
        Integer index = (requestJson.getInteger("current") - 1) * requestJson.getInteger("pageSize");

        result.put("current", requestJson.getInteger("current"));
        result.put("pageSize", requestJson.getInteger("pageSize"));
        result.put("length", appMapper.selectLength());

        List<App> app = appMapper.selectInfo(index, requestJson.getInteger("pageSize"));
        for (App item : app) {
            item.setPort_exist(isPortUsing(Integer.valueOf(item.getPort())));
        }
        result.put("list", app);

        return CommonUtil.successJson(result);
    }

    // 删除应用
    @Override
    public JSONObject delete(Integer id) {
        if (appMapper.delete(id) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.failJson();
    }

    public static boolean isPortUsing(Integer port) throws UnknownHostException {
        InetAddress Address = InetAddress.getByName("127.0.0.1");
        try {
            Socket socket = new Socket(Address, port);  //建立一个Socket连接
            return true;
        } catch (IOException e) {
        }
        return false;
    }
}
