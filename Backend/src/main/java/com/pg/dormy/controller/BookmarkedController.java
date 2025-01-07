package com.pg.dormy.controller;

import com.pg.dormy.DTO.BookmarkedDTO;
import com.pg.dormy.service.BookmarkedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkedController {
    private final BookmarkedService bookmarkedService;

    @Autowired
    public BookmarkedController(BookmarkedService bookmarkedService) {
        this.bookmarkedService = bookmarkedService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserBookmarks(@PathVariable String userId) {
        try {
            Integer userIdInt = validateAndParseId(userId, "User ID");
            List<BookmarkedDTO> bookmarks = bookmarkedService.getBookmarksByUserId(userIdInt);

            if (bookmarks.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(bookmarks);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid User ID format: must be a number");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching bookmarks: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBookmark(
            @RequestParam String userId,
            @RequestParam(required = false) String pgId,
            @RequestParam(required = false) String rentalId) {
        try {
            Integer userIdInt = validateAndParseId(userId, "User ID");
            Integer pgIdInt = pgId != null ? validateAndParseId(pgId, "PG ID") : null;
            Integer rentalIdInt = rentalId != null ? validateAndParseId(rentalId, "Rental ID") : null;

            if (pgIdInt == null && rentalIdInt == null) {
                return ResponseEntity.badRequest()
                        .body("Either PG ID or Rental ID must be provided");
            }

            // Check if already bookmarked
            if (bookmarkedService.isBookmarked(userIdInt, pgIdInt, rentalIdInt)) {
                return ResponseEntity.badRequest()
                        .body("Already bookmarked by user");
            }

            // Add bookmarks based on provided IDs
            if (pgIdInt == null) {
                bookmarkedService.addBookmark(userIdInt, null, rentalIdInt);
            } else if (rentalIdInt == null) {
                bookmarkedService.addBookmark(userIdInt, pgIdInt, null);
            } else {
                // Check both PG and Rental bookmarks separately
                if (bookmarkedService.isBookmarked(userIdInt, pgIdInt, null)) {
                    return ResponseEntity.badRequest()
                            .body("PG already bookmarked by user");
                }
                if (bookmarkedService.isBookmarked(userIdInt, null, rentalIdInt)) {
                    return ResponseEntity.badRequest()
                            .body("Rental already bookmarked by user");
                }
                bookmarkedService.addBookmark(userIdInt, pgIdInt, null);
                bookmarkedService.addBookmark(userIdInt, null, rentalIdInt);
            }

            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format: must be a number");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding bookmark: " + e.getMessage());
        }
    }


    @DeleteMapping("/remove")
    public ResponseEntity<?> removeBookmark(
            @RequestParam String userId,
            @RequestParam(required = false) String pgId,
            @RequestParam(required = false) String rentalId) {
        try {
            // Validate userId
            if (userId == null || userId.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("User ID is required");
            }

            // Validate that at least one ID is provided
            if ((pgId == null || pgId.trim().isEmpty()) &&
                    (rentalId == null || rentalId.trim().isEmpty())) {
                return ResponseEntity.badRequest()
                        .body("Either PG ID or Rental ID must be provided");
            }

            // Parse and validate userId
            Integer userIdInt;
            try {
                userIdInt = Integer.parseInt(userId);
                if (userIdInt <= 0) {
                    return ResponseEntity.badRequest().body("User ID must be a positive number");
                }
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid User ID format: must be a number");
            }

            // Parse and validate pgId if provided
            Integer pgIdInt = null;
            if (pgId != null && !pgId.trim().isEmpty()) {
                try {
                    pgIdInt = Integer.parseInt(pgId);
                    if (pgIdInt <= 0) {
                        return ResponseEntity.badRequest().body("PG ID must be a positive number");
                    }
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("Invalid PG ID format: must be a number");
                }
            }

            // Parse and validate rentalId if provided
            Integer rentalIdInt = null;
            if (rentalId != null && !rentalId.trim().isEmpty()) {
                try {
                    rentalIdInt = Integer.parseInt(rentalId);
                    if (rentalIdInt <= 0) {
                        return ResponseEntity.badRequest().body("Rental ID must be a positive number");
                    }
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("Invalid Rental ID format: must be a number");
                }
            }

            // Check if bookmark exists before removing
            if (!bookmarkedService.isBookmarked(userIdInt, pgIdInt, rentalIdInt)) {
                return ResponseEntity.notFound().build();
            }

            bookmarkedService.removeBookmark(userIdInt, pgIdInt, rentalIdInt);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error removing bookmark: " + e.getMessage());
        }
    }


    @GetMapping("/check")
    public ResponseEntity<?> isBookmarked(
            @RequestParam String userId,
            @RequestParam(required = false) String pgId,
            @RequestParam(required = false) String rentalId) {
        try {
            // Validate userId
            if (userId == null || userId.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("User ID is required");
            }

            // Validate that at least one ID is provided
            if ((pgId == null || pgId.trim().isEmpty()) &&
                    (rentalId == null || rentalId.trim().isEmpty())) {
                return ResponseEntity.badRequest()
                        .body("Either PG ID or Rental ID must be provided");
            }

            // Parse and validate userId
            Integer userIdInt;
            try {
                userIdInt = Integer.parseInt(userId);
                if (userIdInt <= 0) {
                    return ResponseEntity.badRequest().body("User ID must be a positive number");
                }
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid User ID format: must be a number");
            }

            // Parse and validate pgId if provided
            Integer pgIdInt = null;
            if (pgId != null && !pgId.trim().isEmpty()) {
                try {
                    pgIdInt = Integer.parseInt(pgId);
                    if (pgIdInt <= 0) {
                        return ResponseEntity.badRequest().body("PG ID must be a positive number");
                    }
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("Invalid PG ID format: must be a number");
                }
            }

            // Parse and validate rentalId if provided
            Integer rentalIdInt = null;
            if (rentalId != null && !rentalId.trim().isEmpty()) {
                try {
                    rentalIdInt = Integer.parseInt(rentalId);
                    if (rentalIdInt <= 0) {
                        return ResponseEntity.badRequest().body("Rental ID must be a positive number");
                    }
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("Invalid Rental ID format: must be a number");
                }
            }

            boolean isBookmarked = bookmarkedService.isBookmarked(userIdInt, pgIdInt, rentalIdInt);
            return ResponseEntity.ok(isBookmarked);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error checking bookmark status: " + e.getMessage());
        }
    }


    private Integer validateAndParseId(String id, String fieldName) {
        try {
            Integer parsedId = Integer.parseInt(id);
            if (parsedId <= 0) {
                throw new IllegalArgumentException(fieldName + " must be a positive number");
            }
            return parsedId;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(fieldName + " must be a valid number");
        }
    }
}



