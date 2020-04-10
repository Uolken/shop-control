package shop.service.impl;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import shop.entity.Product;
import shop.service.MailService;
import shop.service.SettingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailServiceImpl implements MailService {

    private final MailSender emailSender;
    private final SettingService settingService;

    public MailServiceImpl(MailSender emailSender, SettingService settingService) {
        this.emailSender = emailSender;
        this.settingService = settingService;
    }

    @Override
    public void sendMessageToManager(List<Product> products) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("donotreply@gmail.com");
        message.setTo(settingService.getValue("managerEmail"));
        message.setSubject("Оповещения магазина");
        message.setText("Данные товары были просроченные по закупке: " + products.stream().map(Product::getName).collect(Collectors.joining(", ")));
        emailSender.send(message);
    }
}
