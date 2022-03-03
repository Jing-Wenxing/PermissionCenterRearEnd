package atd.code.permission.mapper;

import atd.code.permission.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    // 新增角色
    Integer create(Role role);

    // 分页查询
    Integer selectLength();
    List<Role> selectInfo(Integer start, Integer pageSize);

    // 删除角色
    Integer delete(Integer id);
}
