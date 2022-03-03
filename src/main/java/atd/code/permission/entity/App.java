package atd.code.permission.entity;

public class App {
    /**
     * 应用管理实体类
     * sql: permission_application
     */

    // 编号
    Integer id;
    // 应用名称
    String name;
    // 应用描述
    String description;
    // 开放端口
    String port;
    // 权限字符串
    String permission;

    // ==========

    // 端口是否被使用，校验后端存在状态
    Boolean port_exist;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getPort_exist() {
        return port_exist;
    }

    public void setPort_exist(Boolean port_exist) {
        this.port_exist = port_exist;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", port='" + port + '\'' +
                ", permission='" + permission + '\'' +
                ", port_exist=" + port_exist +
                '}';
    }
}
