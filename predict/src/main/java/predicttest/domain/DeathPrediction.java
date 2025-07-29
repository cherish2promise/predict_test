package predicttest.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import predicttest.PredictApplication;
import predicttest.domain.DeathPredictedEvent;

@Entity
@Table(name = "DeathPrediction_table")
@Data
//<<< DDD / Aggregate Root
public class DeathPrediction {

    @Id
    private Date date;
    
    private String region;

    private Long predictedDeaths;

    @PostPersist
    public void onPostPersist() {
        DeathPredictedEvent deathPredictedEvent = new DeathPredictedEvent(this);
        deathPredictedEvent.publishAfterCommit();
    }

    public static DeathPredictionRepository repository() {
        DeathPredictionRepository deathPredictionRepository = PredictApplication.applicationContext.getBean(
            DeathPredictionRepository.class
        );
        return deathPredictionRepository;
    }
}
//>>> DDD / Aggregate Root
