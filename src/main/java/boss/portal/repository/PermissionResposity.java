package boss.portal.repository;

import boss.portal.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionResposity extends JpaRepository<SysPermission, Long> {

    public   SysPermission findById(Long id);
}
