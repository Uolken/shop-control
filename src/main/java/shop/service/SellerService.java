package shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import shop.entity.Seller;

public interface SellerService extends UserDetailsService {

    void saveSeller(Seller seller);

    Seller getById(Long id);

    void deleteById(Long id);

}
