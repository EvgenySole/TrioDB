package com.example.triodb.waste_types;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WasteTypeRepository extends JpaRepository<WasteTypes, Long> {
    @Query("SELECT s FROM WasteTypes s WHERE s.wastetId = ?1")
    Optional<WasteTypes> findWasteTypeById(String wastetId);
}
