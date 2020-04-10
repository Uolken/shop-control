package shop.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.entity.Product;
import shop.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCheckServiceImpl implements ProductCheckService {

    private final MailService mailService;
    private final ProductService productService;
    private final BatchService batchService;
    private final SettingService settingService;

    @Override
    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void check() {
        Iterable<Product> products = productService.getAllByRecommendedDate(LocalDate.now().minusDays(1));
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);
        if (productList.size() > 0) {
            mailService.sendMessageToManager(productList);
        }
    }

    public ProductCheckServiceImpl(MailService mailService, ProductService productService, BatchService batchService, SettingService settingService) {
        this.mailService = mailService;
        this.productService = productService;
        this.batchService = batchService;
        this.settingService = settingService;
    }
}
