package boss.portal.repository;

import boss.portal.entity.SysPermission;
import boss.portal.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysRoleResposity extends JpaRepository<SysRole, Long> {

    public SysRole findById(Long id);
}
