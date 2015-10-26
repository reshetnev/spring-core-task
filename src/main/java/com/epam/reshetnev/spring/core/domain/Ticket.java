package com.epam.reshetnev.spring.core.domain;

import java.time.LocalDateTime;

public class Ticket {

    private Integer id;

    private Event event;

    private LocalDateTime airDateTime;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getAirDateTime() {
        return airDateTime;
    }

    public void setAirDateTime(LocalDateTime airDateTime) {
        this.airDateTime = airDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", event=" + event + ", airDateTime=" + airDateTime + ", user=" + user + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airDateTime == null) ? 0 : airDateTime.hashCode());
        result = prime * result + ((event == null) ? 0 : event.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        if (airDateTime == null) {
            if (other.airDateTime != null)
                return false;
        } else if (!airDateTime.equals(other.airDateTime))
            return false;
        if (event == null) {
            if (other.event != null)
                return false;
        } else if (!event.equals(other.event))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

}
