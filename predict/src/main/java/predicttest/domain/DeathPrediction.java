package predicttest.domain;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DeathPrediction_table")
@Data
@IdClass(DeathPredictionId.class)
public class DeathPrediction {

    @Id
    private LocalDate date;
    @Id
    private String region;

    private Long Deaths; // <-- 필드 이름을 "Deaths"로 통일

    public DeathPrediction() {}

    public DeathPrediction(LocalDate date, String region, Long Deaths) { // <-- 생성자 인자 변경
        this.date = date;
        this.region = region;
        this.Deaths = Deaths; // <-- Deaths 필드 초기화
    }

    // lombok.Data가 Getter/Setter를 자동으로 생성해주므로, 명시적으로 선언할 필요는 없습니다.
    // 하지만 가독성을 위해 예시로 표기하자면:
    // public Long getDeaths() { return Deaths; }
    // public void setDeaths(Long deaths) { Deaths = deaths; }
}