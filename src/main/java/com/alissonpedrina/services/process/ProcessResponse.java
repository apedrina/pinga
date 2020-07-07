package com.alissonpedrina.services.process;

import java.util.Date;
import java.util.Objects;

public class ProcessResponse {
    private Date time;
    private String type;
    private String host;
    private String raw;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessResponse that = (ProcessResponse) o;
        return type.equals(that.type) &&
                host.equals(that.host);

    }

    @Override
    public int hashCode() {
        return Objects.hash(type, host);

    }

}
