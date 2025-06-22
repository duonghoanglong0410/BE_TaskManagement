package org.backend.service;

import org.backend.dtos.GroupMember.GroupMemberRequest;
import org.backend.dtos.GroupMember.GroupMemberResponse;
import org.backend.dtos.GroupMember.JoinGroupRequest;

import java.util.List;

public interface GroupMemberService {
    GroupMemberResponse addMember(GroupMemberRequest request);
    List<GroupMemberResponse> getMembersByGroupId(Long groupId);
    GroupMemberResponse updateMemberRole(Long groupMemberId, String newRole);
    void removeMember(Long groupMemberId);
    GroupMemberResponse joinGroup(JoinGroupRequest request);

}
