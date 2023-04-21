package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/api/memberships")

public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    @GetMapping
    public List<Membership> findAll() {
        return membershipService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Membership> findById(@PathVariable String id) {
        return membershipService.findById(id);
    }

    @GetMapping("/class-schedules")
    public List<ClassSchedule> getIndividualClassSchedules(@RequestParam("userId") String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<ClassSchedule> allClassSchedules = classScheduleRepository.findAll();
        return allClassSchedules.stream()
                .filter(classSchedule -> classSchedule.getEnrolledUsers().contains(user))
                .collect(Collectors.toList());
    }

    @PostMapping("/add-membership")
    public Membership save(@RequestBody Membership membership) {
        return membershipService.save(membership);
    }


    @PutMapping("/{id}")
    public Membership update(@PathVariable String id, @RequestBody Membership updatedMembership) {
        Optional<Membership> membershipOptional = membershipService.findById(id);
        if (membershipOptional.isPresent()) {
            Membership membership = membershipOptional.get();
            membership.setName(updatedMembership.getName());
            membership.setPrice(updatedMembership.getPrice());
            membership.setDuration(updatedMembership.getDuration());
            return membershipService.save(membership);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        membershipService.deleteById(id);
    }
}
