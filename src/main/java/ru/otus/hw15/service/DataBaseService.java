package ru.otus.hw15.service;

import org.springframework.stereotype.Service;
import ru.otus.hw15.model.Promotion;

@Service
public class DataBaseService {

    public Promotion process(Promotion promotion) {
        promotion.setModel("modelFromDataBase");
        return promotion;
    }
}
