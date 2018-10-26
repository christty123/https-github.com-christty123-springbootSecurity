package boss.portal.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import boss.portal.entity.SysPermission;
import boss.portal.entity.SysPermissionRole;
import boss.portal.entity.SysRole;
import boss.portal.repository.PermissionResposity;
import boss.portal.repository.SysPermissionRoleResposity;
import boss.portal.repository.SysRoleResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private PermissionResposity permissionResposity;

	@Autowired
	private SysPermissionRoleResposity sysPermissionRoleResposity;

	@Autowired
	private SysRoleResposity sysRoleResposity;


	// map key role value all permission
	private HashMap<String, Collection<ConfigAttribute>> map = null;

	/**
	 * 加载权限表中所有权限
	 */
	@PostConstruct
	public void loadResourceDefine() {
		map = new HashMap<>();
		List<SysPermission> permissions = permissionResposity.findAll();
		for (SysPermission permission : permissions) {
			Collection<ConfigAttribute> array = new ArrayList();;
			List<SysPermissionRole>sprs=sysPermissionRoleResposity.findByPermissionId(permission.getId());
			for(SysPermissionRole sr:sprs) {
				SysRole role = sysRoleResposity.findById(sr.getRoleId());
				if(null!=role){
					ConfigAttribute cfg= new SecurityConfig(role.getName());
					array.add(cfg);
				}
			}
			// 用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
			map.put(permission.getUrl(), array);
		}

	}

	// 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 中包含用户请求的request 信息
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		PathMatcher matcher=new AntPathMatcher();
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
			String partten=iter.next();
			if (matcher.match(partten, reqUrl)) {
				System.out.println(map.get(partten));
				return map.get(partten);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
