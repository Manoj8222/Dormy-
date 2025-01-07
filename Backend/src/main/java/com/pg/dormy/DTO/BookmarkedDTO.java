package com.pg.dormy.DTO;

public class BookmarkedDTO {
    private Integer bookmarkedId;
    private Integer userId;
    private PgDataDTO pgData;
    private RentalDTO rental;

    // Getters and Setters
    public Integer getBookmarkedId() {
        return bookmarkedId;
    }

    public void setBookmarkedId(Integer bookmarkedId) {
        this.bookmarkedId = bookmarkedId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public PgDataDTO getPgData() {
        return pgData;
    }

    public void setPgData(PgDataDTO pgData) {
        this.pgData = pgData;
    }

    public RentalDTO getRental() {
        return rental;
    }

    public void setRental(RentalDTO rental) {
        this.rental = rental;
    }
}
