package com.pg.dormy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bookmarked")
public class Bookmarked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmarked_id")
    private Integer bookmarkedId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User userData;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "pg_id")
    private Integer pgId;

    @ManyToOne
    @JoinColumn(name = "pg_id", insertable=false, updatable=false)
    private PgData pgData;

    @ManyToOne
    @JoinColumn(name = "rental_id", insertable=false, updatable=false)
    private Rental rental;

    @Column(name = "rental_id")
    private Integer rentalId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookmarkedId() {
        return bookmarkedId;
    }

    public void setBookmarkedId(Integer bookmarkedId) {
        this.bookmarkedId = bookmarkedId;
    }

    public Integer getPgId() {
        return pgId;
    }

    public void setPgId(Integer pgId) {
        this.pgId = pgId;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public PgData getPgData() {
        return pgData;
    }

    public void setPgData(PgData pgData) {
        this.pgData = pgData;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
