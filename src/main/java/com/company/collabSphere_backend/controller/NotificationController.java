package com.company.collabSphere_backend.controller;


import com.company.collabSphere_backend.entity.Notification;
import com.company.collabSphere_backend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/")
    public Notification createNotification(
            @RequestParam Long userId,
            @RequestParam String type,
            @RequestParam String message,
            @RequestParam Long relatedEntityId
    ) {
        return notificationService.createNotification(userId, type, message, relatedEntityId);
    }
}
