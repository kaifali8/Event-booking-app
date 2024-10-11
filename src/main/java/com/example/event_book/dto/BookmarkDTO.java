package com.example.event_book.dto;

import lombok.Data;

@Data
public class BookmarkDTO {
    private Long bookmarkId;
    private Long userId;
    private String eventName;
    private String eventCategory;
    private String eventLocation;
    private String eventCity;
    private String eventPoster;
    private String eventDate;
    private String eventTime;
}
