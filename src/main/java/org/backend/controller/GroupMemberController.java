package org.backend.controller;

import org.backend.dtos.GroupMember.GroupMemberRequest;
import org.backend.dtos.GroupMember.GroupMemberResponse;
import org.backend.dtos.GroupMember.JoinGroupRequest;
import org.backend.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-members")
public class GroupMemberController {

    @Autowired
    private GroupMemberService groupMemberService;

    @PostMapping("/join")
    public ResponseEntity<GroupMemberResponse> joinGroup(@RequestBody JoinGroupRequest request) {
        return ResponseEntity.ok(groupMemberService.joinGroup(request));
    }


    @PostMapping
    public ResponseEntity<GroupMemberResponse> addMember(@RequestBody GroupMemberRequest request) {
        return ResponseEntity.ok(groupMemberService.addMember(request));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupMemberResponse>> getMembers(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupMemberService.getMembersByGroupId(groupId));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<GroupMemberResponse> updateRole(
            @PathVariable Long id,
            @RequestParam String role) {
        System.out.println("Changing role of memberId: " + id + " to: " + role);
        return ResponseEntity.ok(groupMemberService.updateMemberRole(id, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        groupMemberService.removeMember(id);
        return ResponseEntity.noContent().build();
    }
}
