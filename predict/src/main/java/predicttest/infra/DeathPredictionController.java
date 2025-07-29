package predicttest.infra;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import predicttest.domain.*;
import predicttest.dto.AiRequestDto; // <-- DTO 클래스 임포트 (새로운 패키지 및 파일)

@RestController
@RequestMapping(value="/deathPredictions")
@Transactional
public class DeathPredictionController {

    @Autowired
    DeathPredictionRepository deathPredictionRepository;

    @Autowired
    DeathPredictionService deathPredictionService;

    @PostMapping
    public ResponseEntity<Void> requestDeathPrediction(@RequestBody AiPredictionRequestDto requestDto) {
        try {
            deathPredictionService.requestNewPrediction(
                requestDto.getDate(),
                requestDto.getRegion(),
                requestDto.getPreviousYearDeaths()
            );
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.err.println("### 예측 요청 처리 중 오류 발생 (Controller): " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{date}/{region}")
    public ResponseEntity<DeathPrediction> getDeathPrediction(
        @PathVariable @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate date,
        @PathVariable String region
    ) {
        Optional<DeathPrediction> deathPredictionOptional = deathPredictionService.getDeathPrediction(date, region);
        if (deathPredictionOptional.isPresent()) {
            return new ResponseEntity<>(deathPredictionOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DeathPrediction>> getAllDeathPredictions() {
        List<DeathPrediction> deathPredictions = deathPredictionService.getAllDeathPredictions();
        return new ResponseEntity<>(deathPredictions, HttpStatus.OK);
    }
}