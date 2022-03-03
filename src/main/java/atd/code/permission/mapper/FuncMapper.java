package atd.code.permission.mapper;

import atd.code.permission.entity.App;
import atd.code.permission.entity.Function;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FuncMapper {
    // 新增功能
    Integer create(Function function);

    // 分页查询
    Integer selectLength(Integer appid);
    List<Function> selectInfo(Integer start, Integer pageSize, Integer appid);

    // 应用信息查询
    App getAppInfo(Integer appid);

    // 删除功能
    Integer delete(Integer id);

    // 删除功能依赖权限
    Integer deletePermOfFunc(Integer funcid);
}
