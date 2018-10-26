package boss.portal.repository;

import boss.portal.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysUserResposity  extends JpaRepository<SysUser, Long> {

}
