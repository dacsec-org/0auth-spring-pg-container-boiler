package org.dacsec.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockRepository extends JpaRepository<Mock, Long> {
    Mock findByName(String name);
}
