package boss.portal.repository;

import boss.portal.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author christ on 2017/9/13.
 */
public interface UserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);

}
