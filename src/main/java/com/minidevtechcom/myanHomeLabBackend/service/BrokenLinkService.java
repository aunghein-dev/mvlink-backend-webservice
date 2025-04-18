package com.minidevtechcom.myanHomeLabBackend.service;


import com.minidevtechcom.myanHomeLabBackend.model.BrokenLink;
import com.minidevtechcom.myanHomeLabBackend.repository.BrokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrokenLinkService implements BrokenLinkServiceInterface {

    @Autowired
    private BrokenRepository repo;


    @Override
    public BrokenLink save(BrokenLink brokenLink) {
        return repo.save(brokenLink);
    }

    @Override
    public Optional<BrokenLink> findById(int id) {
        return repo.findById(id);
    }

    public List<BrokenLink> getAllBrokenLink() {
        return repo.findAll();
    }

    public Optional<BrokenLink> findByTmdbId(int tmdbId) {
        return repo.findById(tmdbId);
    }
}
