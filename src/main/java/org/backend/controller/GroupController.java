package org.backend.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.dtos.group.GroupRequest;
import org.backend.dtos.group.GroupResponse;
import org.backend.models.Group;
import org.backend.models.User;
import org.backend.repository.GroupRepository;
import org.backend.repository.UserRepository;
import org.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping(consumes = {"multipart/form-data"})
    public GroupResponse createGroup(
            @RequestPart("group") String groupJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        GroupRequest request = mapper.readValue(groupJson, GroupRequest.class);
        return groupService.createGroup(request, file);
    }


    @GetMapping("/{id}")
    public GroupResponse getGroup(@PathVariable Long id) {
        return groupService.getGroup(id);
    }

    @GetMapping
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PutMapping("/{id}")
    public GroupResponse updateGroup(@PathVariable Long id, @RequestBody GroupRequest request, MultipartFile file) throws IOException {
        return groupService.updateGroup(id, request, file);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    // ----------- UPLOAD AVATAR TO CLOUDINARY -------------
    @PostMapping("/{groupId}/avatar")
    public GroupResponse uploadAvatar(
            @PathVariable Long groupId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Optional<Group> groupOpt = groupRepository.findById(groupId);
        if (groupOpt.isEmpty()) {
            throw new RuntimeException("Group not found!");
        }

        // Upload file lên Cloudinary
        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "group-avatars",
                        "public_id", "group_" + groupId
                )
        );

        String avatarUrl = (String) uploadResult.get("secure_url");

        // Update avatarUrl cho group
        Group group = groupOpt.get();
        group.setAvatarUrl(avatarUrl);
        groupRepository.save(group);

        // Trả về GroupResponse (dùng GroupService hoặc GroupMapper tùy thiết kế)
        // Nếu muốn update luôn trong cache hoặc service, bạn có thể gọi lại service.
        return groupService.getGroup(groupId);
    }
}
