package org.backend.service;

import org.backend.dtos.group.GroupRequest;
import org.backend.dtos.group.GroupResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GroupService {
    GroupResponse createGroup(GroupRequest request, MultipartFile file);
    GroupResponse getGroup(Long id);
    List<GroupResponse> getAllGroups();
    GroupResponse updateGroup(Long id, GroupRequest request);
    void deleteGroup(Long id);
}