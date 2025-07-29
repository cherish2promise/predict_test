package predicttest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import predicttest.domain.*;
import predicttest.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeathPredictedEvent extends AbstractEvent {

    
    private Date date;
    private String region;
    private Long predictedDeaths;

    public DeathPredictedEvent(DeathPrediction aggregate) {
        super(aggregate);
    }

    public DeathPredictedEvent() {
        super();
    }
}
//>>> DDD / Domain Event
