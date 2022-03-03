package atd.code.permission.entity;

import java.util.List;

public class UserInfo {
    String uuid;
    String username;
    String fullname;
    String birth;
    Integer sexy;
    String telephone;
    String qq;
    String role;
    Integer roleid;
    // 角色信息
    Role roleInfo;
    // 权限信息
    List<Authority> authorityInfoList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Integer getSexy() {
        return sexy;
    }

    public void setSexy(Integer sexy) {
        this.sexy = sexy;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Role getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(Role roleInfo) {
        this.roleInfo = roleInfo;
    }

    public List<Authority> getAuthorityInfoList() {
        return authorityInfoList;
    }

    public void setAuthorityInfoList(List<Authority> authorityInfoList) {
        this.authorityInfoList = authorityInfoList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", birth='" + birth + '\'' +
                ", sexy=" + sexy +
                ", telephone='" + telephone + '\'' +
                ", qq='" + qq + '\'' +
                ", role='" + role + '\'' +
                ", roleid='" + roleid + '\'' +
                ", roleInfo=" + roleInfo +
                ", authorityInfoList=" + authorityInfoList +
                '}';
    }
}
