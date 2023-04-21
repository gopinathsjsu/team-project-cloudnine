package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    @Override
    public Optional<Membership> findById(String id) {
        return membershipRepository.findById(id);
    }

    @Override
    public Membership save(Membership membership) {
        return membershipRepository.save(membership);
    }

    @Override
    public void deleteById(String id) {
        membershipRepository.deleteById(id);
    }
}
