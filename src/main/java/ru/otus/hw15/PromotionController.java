package ru.otus.hw15;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw15.model.Promotion;
import ru.otus.hw15.promotion.PromotionService;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping(value = "/{location}")
    public String getPromotion(@PathVariable String location) {
//        Map<String, String> result = promotionRepository.findAll();
//                .stream()
//                .collect(Collectors.toMap(Promotion::getPromoName, promotion -> promotion.getDataSource().name()));
        return promotionService.process(location);
    }
}
