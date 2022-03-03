package atd.code.permission.mapper;

import atd.code.permission.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermMapper {
    // 获取应用列表
    List<App> getAppList();

    // 获取应用功能列表
    List<Function> getAppIdToFuncList(Integer appid, Integer level);

    // 获取角色信息
    Role getRoleInfo(Integer roleid);

    // 获取用户信息
    UserInfo getUserInfo(String uuid);

    // 应用使用情况
    Authority roleHasAuthority(Integer roleid, Integer funcid);

    // 新增授权
    Integer create(Authority authority);

    Integer delete(String id);
}
