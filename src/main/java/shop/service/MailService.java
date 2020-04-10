package shop.service;

import shop.entity.Batch;
import shop.entity.Product;

import java.util.List;

public interface MailService {
    void sendMessageToManager(List<Product> products);
}
