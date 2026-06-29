package org.unibl.etf.model;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

    @Override
    public LocalTime unmarshal(String v) throws Exception {
        return (v == null) ? null : LocalTime.parse(v, FORMATTER);
    }

    @Override
    public String marshal(LocalTime v) throws Exception {
        return (v == null) ? null : v.format(FORMATTER);
    }
}
