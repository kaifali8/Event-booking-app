package com.example.event_book.controller;

import com.example.event_book.model.Bookmark;
import com.example.event_book.service.BookmarkService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    //create a bookmark for user of an event
    @PostMapping
    public ResponseEntity<Bookmark> createBookmark(@RequestParam Long userId, @RequestParam Long eventId){
        Bookmark bookmark=bookmarkService.createBookmark(userId, eventId);
        return ResponseEntity.status(201).body(bookmark);
    }

    //get all bookmarks of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bookmark>> getUserBookmarks(@PathVariable Long userId){
        List<Bookmark> bookmarks=bookmarkService.getUserBookmarks(userId);
        return ResponseEntity.ok(bookmarks);
    }

    //delete a bookmark
    @DeleteMapping("/user/{userId}/event/{eventId}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long userId,@PathVariable Long eventId){
        bookmarkService.deleteBookmark(userId, eventId);
        return ResponseEntity.noContent().build();
    }
}
