package io.pivotal.pal.tracker;


import java.util.List;

public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntry);
    List<TimeEntry> list();
    TimeEntry find(long timeEntryId);
    TimeEntry update(long entryId, TimeEntry timeEntry);
    void delete(long entryId);
}
