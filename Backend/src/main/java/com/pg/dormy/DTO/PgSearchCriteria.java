package com.pg.dormy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class PgSearchCriteria {
    private String pgArea;
    private String gender;
    private Boolean hasSingle;
    private Boolean hasDouble;
    private Boolean hasTriple;
    private Boolean hasQuadruple;
    private BigDecimal minRent;
    private BigDecimal maxRent;

    public PgSearchCriteria() {
    }

    public PgSearchCriteria(String pgArea, String gender, Boolean hasSingle, Boolean hasDouble, Boolean hasTriple, Boolean hasQuadruple, BigDecimal minRent, BigDecimal maxRent) {
        this.pgArea = pgArea;
        this.gender = gender;
        this.hasSingle = hasSingle;
        this.hasDouble = hasDouble;
        this.hasTriple = hasTriple;
        this.hasQuadruple = hasQuadruple;
        this.minRent = minRent;
        this.maxRent = maxRent;
    }

    public String getPgArea() {
        return pgArea;
    }

    public void setPgArea(String pgArea) {
        this.pgArea = pgArea;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getHasSingle() {
        return hasSingle;
    }

    public void setHasSingle(Boolean hasSingle) {
        this.hasSingle = hasSingle;
    }

    public Boolean getHasDouble() {
        return hasDouble;
    }

    public void setHasDouble(Boolean hasDouble) {
        this.hasDouble = hasDouble;
    }

    public Boolean getHasTriple() {
        return hasTriple;
    }

    public void setHasTriple(Boolean hasTriple) {
        this.hasTriple = hasTriple;
    }

    public Boolean getHasQuadruple() {
        return hasQuadruple;
    }

    public void setHasQuadruple(Boolean hasQuadruple) {
        this.hasQuadruple = hasQuadruple;
    }

    public BigDecimal getMinRent() {
        return minRent;
    }

    public void setMinRent(BigDecimal minRent) {
        this.minRent = minRent;
    }

    public BigDecimal getMaxRent() {
        return maxRent;
    }

    public void setMaxRent(BigDecimal maxRent) {
        this.maxRent = maxRent;
    }
}