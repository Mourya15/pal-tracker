package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;

import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{


    private long id=0L;
    Map<Long, TimeEntry> m=new HashMap<Long, TimeEntry>();;

    public TimeEntry create(TimeEntry timeEntry)
    {
        //System.out.println(timeEntry);
        id=id+1;
        timeEntry.setId(id);
        m.put(id, timeEntry);
        //System.out.println(timeEntry);
        return timeEntry;
    }
    public TimeEntry find(long timeEntryId)
    {
       if(m.containsKey(timeEntryId)) {
           return m.get(timeEntryId);
       }
       return null;
    }
    public List<TimeEntry> list()
    {
        List<TimeEntry> list=new ArrayList<TimeEntry>();
        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry)it.next();
            list.add((TimeEntry)pair.getValue());
        }

        return list;
    }
    public TimeEntry update(long entryId, TimeEntry timeEntry)
    {
        if(m.containsKey(entryId)) {
            timeEntry.setId(entryId);
            m.put(entryId, timeEntry);
            return timeEntry;
        }
        return null;
    }
    public void delete(long entryId)
    {
        m.remove(entryId);
    }


}
