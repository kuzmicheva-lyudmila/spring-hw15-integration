package ru.otus.hw15.service;

import org.springframework.stereotype.Service;
import ru.otus.hw15.model.Promotion;

@Service
public class MlService {

    public Promotion process(Promotion promotion) {
        promotion.setModel("modelFromMlService");
        return promotion;
    }
}
