package org.LukaCener.praksa.zad1;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {

    @GET
    @Path("/now")
    public Map<String, Object> now() {
        return Map.of(
                "now", LocalDateTime.now()
        );
    }

    @GET
    @Path("/add-days")
    public Map<String, Object> addDays(@QueryParam("days") long days) {
        LocalDate result = LocalDate.now().plusDays(days);
        return Map.of(
                "daysAdded", days,
                "resultDate", result
        );
    }

    @GET
    @Path("/diff")
    public Map<String, Object> diff(
            @QueryParam("start") String start,
            @QueryParam("end") String end
    ) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long diff = ChronoUnit.DAYS.between(startDate, endDate);

        return Map.of(
                "start", startDate,
                "end", endDate,
                "differenceInDays", diff
        );
    }
}