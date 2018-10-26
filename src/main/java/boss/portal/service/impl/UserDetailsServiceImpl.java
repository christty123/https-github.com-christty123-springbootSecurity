package boss.portal.service.impl;

import boss.portal.entity.SysRole;
import boss.portal.entity.SysRoleUser;
import boss.portal.entity.SysUser;
import boss.portal.entity.UserDetail;
import boss.portal.repository.SysRoleResposity;
import boss.portal.repository.SysRoleUserResposity;
import boss.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author christ on 2017/9/13.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SysRoleUserResposity sysRoleUserResposity;

    @Autowired
    private SysRoleResposity sysRoleResposity;

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetail userDetail = new UserDetail();
        userDetail.setId(user.getId());
        userDetail.setUsername(user.getUsername());
        userDetail.setPassword(user.getPassword());

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        List<SysRoleUser> roleUsers = sysRoleUserResposity.findBySysUserId(user.getId());
        for (SysRoleUser roleUser : roleUsers) {
            SysRole role=sysRoleResposity.findById(roleUser.getSysRoleId());
            authorities.add(new GrantedAuthorityImpl(role.getName()));
        }
        userDetail.setAuthorities(authorities);

        return userDetail;
    }

}
