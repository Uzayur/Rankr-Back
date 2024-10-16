package com.mathis.rankr.controllers;

import com.mathis.rankr.exceptions.UserNotFoundException;
import com.mathis.rankr.models.friend.Friendship;
import com.mathis.rankr.repository.FriendRepository;
import com.mathis.rankr.services.FriendService;
import com.mathis.rankr.services.UserService;
import com.mathis.rankr.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;
    private final UserService userService;

    @Autowired
    public FriendController(FriendService friendService, UserService userService, FriendRepository friendRepository) {
        this.friendService = friendService;
        this.userService = userService;
    }

    @PostMapping("/request/{uuid}")
    public ResponseEntity<?> sendFriendRequest(@PathVariable UUID uuid) {
        try {
            UUID userId = userService.getConnectedUserUuid();

            Friendship newFriendship = new Friendship(userId, uuid);
            Friendship createdFriendship = friendService.createFriendship(newFriendship);
            return ResponseUtil.body(HttpStatus.CREATED, createdFriendship);
        } catch(UserNotFoundException e) {
            return ResponseUtil.build(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseUtil.build(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.build(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
