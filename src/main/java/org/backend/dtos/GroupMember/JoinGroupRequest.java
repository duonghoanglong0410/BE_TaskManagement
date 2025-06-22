package org.backend.dtos.GroupMember;

public class JoinGroupRequest {
    private String groupCode;
    private String password;

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGroupCode() {
        return groupCode;
    }
    public String getPassword() {
        return password;
    }
}
