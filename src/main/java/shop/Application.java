package shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import shop.entity.*;
import shop.repos.*;
import shop.service.ProductCheckService;
import shop.service.SellerService;
import shop.service.SettingService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;

@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(
            ProductRepos productRepos,
            ProducerRepos producerRepos,
            MetricRepos metricRepos,
            BatchRepos batchRepos,
            ProductCheckService productCheckService,
            OrderRepos orderRepos,
            SaleRepos saleRepos,
            SellerService sellerService,
            SettingService settingService) {
        return (args) -> {
            Metric count = new Metric("шт");
            Metric liter = new Metric("л");
            Metric kilogram = new Metric("кг");
            Metric paletka = new Metric("палеток");
            count = metricRepos.save(count);
            liter = metricRepos.save(liter);
            kilogram = metricRepos.save(kilogram);
            paletka = metricRepos.save(paletka);

            Product product = new Product("Яблоки Гала", kilogram, 10L, new HashSet<>(), new HashSet<>(), LocalDate.of(2020, 4, 8));
            product.setRecommendedDate(LocalDate.of(2020, 5, 5));
            Product product1 = new Product("Кефир", paletka, 10L, new HashSet<>(), new HashSet<>(), LocalDate.of(2020, 4, 14));
            Product product2 = new Product("Сгущенка", paletka, 10L, new HashSet<>(), new HashSet<>(), LocalDate.of(2020, 4, 12));
            Product product3 = new Product("Туалетная бумага", count, 10L, new HashSet<>(), new HashSet<>(), LocalDate.of(2020, 04, 10));
            product = productRepos.save(product);
            product1 = productRepos.save(product1);
            product2 = productRepos.save(product2);
            product3 = productRepos.save(product3);

            Producer producer = new Producer("Поставшик яблок", 10, new Address("Saratov", "Lenina", "1A"), 100, 10, null);
            Producer producer1 = new Producer("Поставшик яблок2", 5, new Address("Saratov", "Lenina1", "1A"), 200, 11, null);
            producer = producerRepos.save(producer);
            producer1 = producerRepos.save(producer1);

            producer.setProduct(product);
            producer1.setProduct(product);
            producer = producerRepos.save(producer);
            producer1 = producerRepos.save(producer1);
            product = productRepos.findById(product.getId()).get();

            Order order = new Order(product, producer, LocalDate.of(2020, 4, 8), 10L, 400L, false);
            Order order1 = new Order(product, producer1, LocalDate.of(2020, 4, 10), 14L, 40L, false);
            order = orderRepos.save(order);
            order1 = orderRepos.save(order1);

            Batch batch = new Batch(product, LocalDate.now(), LocalDate.of(2020, 4, 5), 10L);
            Batch batch1 = new Batch(product, LocalDate.now(), LocalDate.of(2020, 4, 6), 11L);
            Batch batch2 = new Batch(product, LocalDate.now(), LocalDate.of(2020, 4, 9), 12L);
            Batch batch3 = new Batch(product1, LocalDate.now(), LocalDate.of(2020, 4, 10), 13L);
            Batch batch4 = new Batch(product1, LocalDate.now(), LocalDate.of(2020, 4, 11), 14L);
            batchRepos.save(batch);
            batchRepos.save(batch1);
            batchRepos.save(batch2);
            batchRepos.save(batch3);
            batchRepos.save(batch4);


            Seller test = new Seller("test", "test", "test", new HashSet<>());
            Seller seller = new Seller("user", "email", "pass", new HashSet<>());
            Seller seller1 = new Seller("user1", "email1", "pass", new HashSet<>());
            Seller seller2 = new Seller("user1", "email1", "pass", new HashSet<>());
            sellerService.saveSeller(test);
            sellerService.saveSeller(seller);
            sellerService.saveSeller(seller1);
            sellerService.saveSeller(seller2);

            settingService.saveSettings("managerEmail", "palashevskiymaxim@gmail.com");
            settingService.saveSettings("recommendedDays", "2");

            LocalDate date = LocalDate.of(2020, 4, 9);
            System.out.println(date);

        };
    }
}
