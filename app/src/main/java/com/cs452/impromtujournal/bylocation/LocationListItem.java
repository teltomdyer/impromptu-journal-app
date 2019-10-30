package com.cs452.impromtujournal.bylocation;

import com.cs452.impromtujournal.model.objects.Entry;

import java.util.List;

public class LocationListItem {
    String city;
    List<Entry> entryList;

    public LocationListItem() {
    }

    public LocationListItem(String city, List<Entry> entryList) {
        this.city = city;
        this.entryList = entryList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
