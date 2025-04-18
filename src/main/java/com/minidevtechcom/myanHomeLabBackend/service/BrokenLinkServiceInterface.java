package com.minidevtechcom.myanHomeLabBackend.service;

import com.minidevtechcom.myanHomeLabBackend.model.BrokenLink;

import java.util.Optional;

public interface BrokenLinkServiceInterface {
    BrokenLink save(BrokenLink brokenLink);

    Optional<BrokenLink> findById(int id);
}
