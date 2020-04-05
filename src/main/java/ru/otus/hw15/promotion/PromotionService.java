package ru.otus.hw15.promotion;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface PromotionService {

    @Gateway(requestChannel = "promotionBegin", replyChannel = "channelResult")
    String process(String location);
}
