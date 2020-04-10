package shop.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shop.entity.Setting;

@Repository
public interface SettingRepos extends CrudRepository<Setting, String> {
    Setting findByName(String name);
}
