package boss.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysRoleUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_user")
public class SysRoleUser implements java.io.Serializable {
	private Long id;
	private Long sysUserId;
	private Long sysRoleId;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "sys_user_id", nullable = false)
	public Long getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	@Column(name = "sys_role_id", nullable = false)
	public Long getSysRoleId() {
		return this.sysRoleId;
	}

	public void setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

}