package com.pg.dormy.service;

import com.pg.dormy.DTO.BookmarkedDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookmarkedService {
    List<BookmarkedDTO> getBookmarksByUserId(Integer userId);
    void addBookmark(Integer userId, Integer pgId, Integer rentalId);
    void removeBookmark(Integer userId, Integer pgId, Integer rentalId);
    boolean isBookmarked(Integer userId, Integer pgId, Integer rentalId);
}