package predicttest.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import predicttest.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "deathPredictions",
    path = "deathPredictions"
)
public interface DeathPredictionRepository
    extends PagingAndSortingRepository<DeathPrediction, Date> {}
