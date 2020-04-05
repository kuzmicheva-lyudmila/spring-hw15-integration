package ru.otus.hw15;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw15.model.Promotion;
import ru.otus.hw15.promotion.PromotionService;
import ru.otus.hw15.repository.PromotionRepository;
import ru.otus.hw15.service.DataBaseService;
import ru.otus.hw15.service.MlService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest("auto.startup=false")
public class TestPromotionService {

    private static final String LOCATION = "MAIN";
    private static final List<Promotion> PROMOTIONS = List.of(
            new Promotion(1L, "banner", LOCATION, Promotion.DataSource.DATABASE, "modelFromDataBase"),
            new Promotion(2L, "tag", LOCATION, Promotion.DataSource.ML_SERVICE, "modelFromMlService")
    );
    private static final String EXPECTED_RESULT =
            "[{\"promoName\":\"banner\",\"model\":\"modelFromDataBase\"},"
                    + "{\"promoName\":\"tag\",\"model\":\"modelFromMlService\"}]";

    @Autowired
    private PromotionService promotionService;

    @Mock
    private PromotionRepository promotionRepository;

    @Mock
    private DataBaseService dataBaseService;

    @Mock
    private MlService mlService;

    @Test
    public void testPromotionService() {
        when(promotionRepository.findAllByLocation(LOCATION)).thenReturn(
                List.of(
                        new Promotion(1L, "banner", LOCATION, Promotion.DataSource.DATABASE, "modelFromDataBase"),
                        new Promotion(2L, "tag", LOCATION, Promotion.DataSource.ML_SERVICE, "modelFromMlService")
                )
        );
        when(dataBaseService.process(any(Promotion.class))).thenReturn(PROMOTIONS.get(0));
        when(mlService.process(any(Promotion.class))).thenReturn(PROMOTIONS.get(1));

        String result = promotionService.process(LOCATION);

        assertEquals(EXPECTED_RESULT, result);
    }
}
