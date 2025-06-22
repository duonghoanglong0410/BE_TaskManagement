package org.backend.repository;

import org.backend.models.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    List<GroupMember> findByGroupId(Long groupId);

    @Modifying
    @Query("DELETE FROM GroupMember gm WHERE gm.id = :id")
    void deleteShallowById(@Param("id") Long id);

    Optional<GroupMember> findByGroupIdAndUserId(Long groupId, Long userId);

    // ✅ Thay vì dùng findById (dễ gây join nặng), dùng query giới hạn:
    @Query("SELECT gm FROM GroupMember gm " +
            "JOIN FETCH gm.user u " +
            "LEFT JOIN FETCH gm.invitedBy ib " +
            "WHERE gm.id = :id")
    Optional<GroupMember> findWithUserAndInviterById(@Param("id") Long id);
}
