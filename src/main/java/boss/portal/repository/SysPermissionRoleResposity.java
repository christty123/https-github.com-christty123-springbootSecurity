package boss.portal.repository;

import boss.portal.entity.SysPermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dell on 2018/10/23.
 */
public interface SysPermissionRoleResposity extends JpaRepository<SysPermissionRole, Long> {

    public List<SysPermissionRole> findByPermissionId(Long permissionId);
}
