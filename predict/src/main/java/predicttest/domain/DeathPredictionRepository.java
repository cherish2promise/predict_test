package predicttest.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import predicttest.domain.DeathPredictionId; // DeathPredictionId 복합 키 클래스 import

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "deathPredictions",
    path = "deathPredictions"
)
public interface DeathPredictionRepository
    extends PagingAndSortingRepository<DeathPrediction, DeathPredictionId> {} // ID 타입을 DeathPredictionId로 변경