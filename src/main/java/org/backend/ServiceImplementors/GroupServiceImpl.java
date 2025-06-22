package org.backend.ServiceImplementors;

import com.cloudinary.utils.ObjectUtils;
import org.backend.dtos.group.GroupRequest;
import org.backend.dtos.group.GroupResponse;
import org.backend.mapper.GroupMapper;
import org.backend.models.Group;
import org.backend.models.User;
import org.backend.repository.GroupRepository;
import org.backend.repository.UserRepository;
import org.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    public GroupResponse createGroup(GroupRequest request, MultipartFile file) {
        // Lấy user đang đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        System.out.println("currentUserEmail = " + currentUserEmail);
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String avatarUrl = null;
        if (file != null && !file.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                avatarUrl = uploadResult.get("secure_url").toString();
            } catch (IOException e) {
                throw new RuntimeException("Upload avatar failed", e);
            }
        }

        Group group = groupMapper.toGroup(request);
        group.setGroupCode("GR" + System.currentTimeMillis());
        group.setAvatarUrl(avatarUrl);
        group.setPassword(passwordEncoder.encode(request.getPassword()));
        group.setCreatedAt(LocalDateTime.now());

        // *** Gán user đang login vào createdBy ***
        group.setCreatedBy(currentUser);

        group = groupRepository.save(group);
        return groupMapper.toGroupResponse(group);
    }

    public GroupResponse getGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return groupMapper.toGroupResponse(group);
    }

    public List<GroupResponse> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::toGroupResponse)
                .collect(Collectors.toList());
    }

    public GroupResponse updateGroup(Long id, GroupRequest request) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        group.setName(request.getName());
        group.setDescription(request.getDescription());
        group.setAvatarUrl(request.getAvatarUrl());
        group.setMaxMembers(request.getMaxMembers());
        group.setStatus(request.getStatus());
        // Nếu muốn cho phép đổi password group (có trường password trong request)
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            group.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Không update avatarUrl ở đây (đã có API upload avatar riêng)
        // Nếu muốn update avatar bằng link:
        // if (request.getAvatarUrl() != null && !request.getAvatarUrl().isBlank()) {
        //     group.setAvatarUrl(request.getAvatarUrl());
        // }

        
        group = groupRepository.save(group);
        return groupMapper.toGroupResponse(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}