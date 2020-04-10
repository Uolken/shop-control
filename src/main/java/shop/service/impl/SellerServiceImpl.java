package shop.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.entity.Seller;
import shop.repos.SellerRepos;
import shop.service.SellerService;

import javax.persistence.OneToMany;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepos sellerRepos;
    private final PasswordEncoder passwordEncoder;

    public SellerServiceImpl(SellerRepos sellerRepos, PasswordEncoder passwordEncoder) {
        this.sellerRepos = sellerRepos;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveSeller(Seller seller) {
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        sellerRepos.save(seller);
    }

    @Override
    public Seller getById(Long id) {
        return sellerRepos.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteById(Long id) {
        sellerRepos.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Seller user = sellerRepos.findByLogin(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
