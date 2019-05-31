package io.pivotal.pal.tracker;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(
            TimeEntryRepository timeEntriesRepo,
            MeterRegistry meterRegistry
    ) {
        this.timeEntryRepository = timeEntriesRepo;

        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    //@RequestMapping(method = RequestMethod.POST, value="/{timeEntryId}" )
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity create(@RequestBody TimeEntry timeEntry)
    {
       TimeEntry timeEntry1= timeEntryRepository.create(timeEntry);
        return new ResponseEntity(timeEntry1,HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET,
            value = "/{timeEntryId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity read(@PathVariable ("timeEntryId") long timeEntryId)
    {


        TimeEntry timeEntry= timeEntryRepository.find(timeEntryId);
        if(timeEntry!=null )
        {
            return new ResponseEntity(timeEntry,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TimeEntry>> list()
    {
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{timeEntryId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TimeEntry> update(@PathVariable("timeEntryId") long timeEntryId, @RequestBody TimeEntry timeEntry)
    {
        TimeEntry timeEntry1=timeEntryRepository.update(timeEntryId, timeEntry);
        if(timeEntry1!=null) {
            return new ResponseEntity( timeEntry1, HttpStatus.OK);
        }
        return new ResponseEntity( HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.DELETE, value="/{timeEntryId}" )
    public  ResponseEntity<TimeEntry> delete(@PathVariable("timeEntryId") long timeEntryId)
    {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
