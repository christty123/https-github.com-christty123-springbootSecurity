package boss.portal.repository;

import boss.portal.entity.SysRole;
import boss.portal.entity.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SysRoleUserResposity extends JpaRepository<SysRoleUser, Long> {

    public List<SysRoleUser> findBySysUserId(Long userId);
}
