package org.backend.repository;

import org.backend.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    boolean existsByGroupCode(String groupCode);
}