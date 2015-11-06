package com.epam.reshetnev.spring.core.domain;

public class Ticket {

    private Integer id;

    private Integer eventId;

    private Integer userId;

    private Integer seat;

    private Boolean isPurchased = false;

    public Ticket() {
    }

    public Ticket(Integer id, Integer eventId, Integer userId, Integer seat, Boolean isPurchased) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.seat = seat;
        this.isPurchased = isPurchased;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(Boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", eventId=" + eventId + ", userId=" + userId + ", seat=" + seat + ", isPurchased="
                + isPurchased + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isPurchased == null) ? 0 : isPurchased.hashCode());
        result = prime * result + ((seat == null) ? 0 : seat.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        if (eventId == null) {
            if (other.eventId != null)
                return false;
        } else if (!eventId.equals(other.eventId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isPurchased == null) {
            if (other.isPurchased != null)
                return false;
        } else if (!isPurchased.equals(other.isPurchased))
            return false;
        if (seat == null) {
            if (other.seat != null)
                return false;
        } else if (!seat.equals(other.seat))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
