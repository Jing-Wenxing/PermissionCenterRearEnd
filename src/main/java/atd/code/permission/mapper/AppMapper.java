package atd.code.permission.mapper;

import atd.code.permission.entity.App;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppMapper {
    // 新增应用
    Integer create(App app);

    // 分页查询
    Integer selectLength();
    List<App> selectInfo(Integer start, Integer pageSize);

    // 删除应用
    Integer delete(Integer id);
}
