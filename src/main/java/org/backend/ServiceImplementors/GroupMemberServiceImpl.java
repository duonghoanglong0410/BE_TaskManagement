package org.backend.ServiceImplementors;
import jakarta.transaction.Transactional;
import org.backend.dtos.GroupMember.GroupMemberRequest;
import org.backend.dtos.GroupMember.GroupMemberResponse;
import org.backend.dtos.GroupMember.JoinGroupRequest;
import org.backend.enums.GroupRole;
import org.backend.mapper.GroupMemberMapper;
import org.backend.models.Group;
import org.backend.models.GroupMember;
import org.backend.models.User;
import org.backend.repository.GroupMemberRepository;
import org.backend.repository.GroupRepository;
import org.backend.repository.UserRepository;
import org.backend.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupMemberMapper groupMemberMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public GroupMemberResponse addMember(GroupMemberRequest request) {
        // Lấy user đang login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        GroupMember groupMember = new GroupMember();
        groupMember.setUser(user);
        groupMember.setInvitedBy(user);
        groupMember.setGroup(group);
        groupMember.setRole(request.getRole());
        groupMember.setJoinedAt(LocalDateTime.now());

        return groupMemberMapper.toGroupMemberResponse(groupMemberRepository.save(groupMember));
    }

    @Override
    public List<GroupMemberResponse> getMembersByGroupId(Long groupId) {
        return groupMemberRepository.findByGroupId(groupId)
                .stream()
                .map(groupMemberMapper::toGroupMemberResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GroupMemberResponse updateMemberRole(Long groupMemberId, String newRole) {
        GroupMember member = groupMemberRepository.findWithUserAndInviterById(groupMemberId)
                .orElseThrow(() -> new RuntimeException("Group member not found"));

        member.setRole(GroupRole.valueOf(newRole));
        return groupMemberMapper.toGroupMemberResponse(groupMemberRepository.save(member));
    }

    @Transactional
    @Override
    public void removeMember(Long groupMemberId) {
        groupMemberRepository.deleteShallowById(groupMemberId);
    }

    @Override
    public GroupMemberResponse joinGroup(JoinGroupRequest request) {
        // Lấy user đang login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Tìm group theo groupCode
        Group group = groupRepository.findByGroupCode(request.getGroupCode())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(request.getPassword(), group.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Kiểm tra nếu đã là thành viên
        if (groupMemberRepository.findByGroupIdAndUserId(group.getId(), user.getId()).isPresent()) {
            throw new RuntimeException("You are already a member of this group");
        }

        // Tạo mới GroupMember
        GroupMember groupMember = new GroupMember();
        groupMember.setGroup(group);
        groupMember.setUser(user);
        groupMember.setRole(GroupRole.valueOf("MEMBER"));
        groupMember.setJoinedAt(LocalDateTime.now());

        return groupMemberMapper.toGroupMemberResponse(groupMemberRepository.save(groupMember));
    }

}
