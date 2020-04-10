package shop.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.entity.Seller;

@Repository
public interface SellerRepos extends CrudRepository<Seller, Long> {
    Seller findByLogin(String login);
}
