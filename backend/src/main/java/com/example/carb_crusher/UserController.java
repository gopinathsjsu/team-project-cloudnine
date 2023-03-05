package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;


@RestController


@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    CheckInService checkInService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MembershipService membershipService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Add validation and error handling
        try {
            User user1 = userService.save(user);
            return ResponseEntity.ok(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
        }
    }


    @GetMapping("/users/{email}")
    public User findByEmail(@PathVariable String email) {
        // Add validation and error handling
        return userService.findByEmail(email);
    }

    @GetMapping("/gym-location/{gymLocationId}")
    public List<User> findByGymLocationId(@PathVariable String gymLocationId) {
        return userService.findByGymLocationId(gymLocationId);
    }


    @GetMapping("/test")
    public String testEndpoint() {
        return "Backend is reachable!";
    }

    @PostMapping("/add-membership")
    public User addMembership(@RequestParam String userId, @RequestParam String membershipId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));
        Membership membership = membershipService.findById(membershipId)
                .orElseThrow(() -> new NotFoundException("Membership not found with id " + membershipId));

        user.setMembership(membership);
        userRepository.save(user);

        return user;
    }

    @PostMapping("/checkin")
    public User checkInUser(@RequestBody CheckInOutRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id " + request.getUserId()));

        // You can create a CheckIn entity and save it to a CheckInRepository if needed.
        user.setLastCheckIn(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }

    @PostMapping("/checkout")
    public User checkOutUser(@RequestBody CheckInOutRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id " + request.getUserId()));

        // You can create a CheckOut entity and save it to a CheckOutRepository if needed.
        user.setLastCheckOut(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }

    @PostMapping("/users/{userId}/checkin")
    public User checkInUser(@PathVariable String userId) {
        return userService.checkInUser(userId, LocalDateTime.now());
    }

    @PostMapping("/users/{userId}/checkout")
    public User checkOutUser(@PathVariable String userId) {
        return userService.checkOutUser(userId, LocalDateTime.now());
    }

    @GetMapping("/users/{userId}/hours-spent/daily")
    public double getDailyHoursSpent(
            @PathVariable String userId,
            @RequestParam("date") String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return userService.calculateDailyHoursSpent(userId, date);
    }

    @GetMapping("/users/{userId}/hours-spent/weekly")
    public double getWeeklyHoursSpent(
            @PathVariable String userId,
            @RequestParam("startDateOfWeek") String startDateOfWeekStr) {
        LocalDate startDateOfWeek = LocalDate.parse(startDateOfWeekStr);
        return userService.calculateWeeklyHoursSpent(userId, startDateOfWeek);
    }

    @GetMapping("/users/{userId}/hours-spent/monthly")
    public double getMonthlyHoursSpent(
            @PathVariable String userId,
            @RequestParam("month") String monthStr) {
        LocalDate localDate = LocalDate.parse(monthStr);
        YearMonth month = YearMonth.from(localDate);
        return userService.calculateMonthlyHoursSpent(userId, month);
    }




    @PostMapping("/trial")
    public User signUpForFreeTrial(@RequestBody FreeTrialRequest request) {
        // Add validation, error handling, and logic for signing up a non-member for a free trial
        // Example: userService.signUpForFreeTrial(request);
        return null;
    }

    @GetMapping("/timeSpentStats")
    public List<TimeSpentStats> getTimeSpentStats(@RequestParam("userId") String userId, @RequestParam("period") String period) {
        return userService.getTimeSpentStats(userId, period);
    }

    @GetMapping("/checkInStats")
    public List<CheckInStats> getCheckInStats(@RequestParam("period") String period, @RequestParam("locationId") String locationId) {
        return checkInService.getCheckInStats(period, locationId);
    }


}
