package atd.code.permission.entity;

public class Function {
    /**
     * 功能管理实体类
     * sql: permission_function
     */

    // 编号
    Integer id;
    // 功能名称
    String name;
    // 功能描述
    String description;
    // 权限字符串
    String permission;
    // 二级权限字符串
    String second_per;
    // 应用所属
    String appid;
    // 功能级别
    String level;

    Boolean user_used;
    Integer authorityid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getSecond_per() {
        return second_per;
    }

    public void setSecond_per(String second_per) {
        this.second_per = second_per;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getUser_used() {
        return user_used;
    }

    public void setUser_used(Boolean user_used) {
        this.user_used = user_used;
    }

    public Integer getAuthorityid() {
        return authorityid;
    }

    public void setAuthorityid(Integer authorityid) {
        this.authorityid = authorityid;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permission='" + permission + '\'' +
                ", second_per='" + second_per + '\'' +
                ", description='" + description + '\'' +
                ", appid='" + appid + '\'' +
                ", level='" + level + '\'' +
                ", user_used=" + user_used +
                ", authorityid=" + authorityid +
                '}';
    }
}
