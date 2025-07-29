package predicttest.domain;

import java.time.LocalDate;
import lombok.Data;
import lombok.ToString;
import predicttest.infra.AbstractEvent;

@Data
@ToString
public class DeathPredictionRequestedEvent extends AbstractEvent {

    private LocalDate date;
    private String region;
    private Long previousYearDeaths; 

    public DeathPredictionRequestedEvent() {
        super();
    }

    public DeathPredictionRequestedEvent(LocalDate date, String region, Long previousYearDeaths) {
        super();
        this.date = date;
        this.region = region;
        this.previousYearDeaths = previousYearDeaths;
    }
}
