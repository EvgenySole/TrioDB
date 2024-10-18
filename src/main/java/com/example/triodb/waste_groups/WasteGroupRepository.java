package com.example.triodb.waste_groups;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WasteGroupRepository extends JpaRepository<WasteGroups, Long> {
    @Query("SELECT s FROM WasteGroups s WHERE s.wastegId = ?1")
    Optional<WasteGroups> findWasteTypeById(String wastetId);
    List<WasteGroups> findAll(Specification<WasteGroups> spec, Pageable page);
}
