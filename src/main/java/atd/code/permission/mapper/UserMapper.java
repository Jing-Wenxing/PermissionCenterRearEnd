package atd.code.permission.mapper;

import atd.code.permission.entity.Authority;
import atd.code.permission.entity.Role;
import atd.code.permission.entity.User;
import atd.code.permission.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserInfo getUUIDToUserInfo(String uuid);

    // 获取用户分页列表
    Integer selectLength();
    List<User> selectInfo(Integer start, Integer pageSize, Integer level);

    // 全角色列表
    List<Role> roleList(Integer level);

    // 获取用户权限列表
    List<Authority> authorityList(Integer roleid);

    // 获取用户角色信息
    Role getRoleInfo(String role);

    Integer updateUserRole(String uuid, String role, String roleid);
}
