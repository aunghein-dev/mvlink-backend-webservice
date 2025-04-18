package com.minidevtechcom.myanHomeLabBackend.repository;

import com.minidevtechcom.myanHomeLabBackend.model.BrokenLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokenRepository extends JpaRepository<BrokenLink, Integer> {
}
