package com.jeecloud.common.entity.system;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeecloud.common.validator.AddGroup;
import com.jeecloud.common.validator.UpdateGroup;

/**
 * @ClassName: SysUser
 * @Description: 系统用户
 * @author: admin
 * @date: 2018年3月6日 下午4:18:10
 */
public class SysUser implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private Long id;
	/**
	 * 部门ID
	 */
	private Long deptId;
	/**
	 * 企业ID
	 */
	private Long companyId;
	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空", groups = { AddGroup.class, UpdateGroup.class })
	private String username;
	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空", groups = AddGroup.class)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	/**
	 * 盐加密
	 */
	private String salt;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 邮箱
	 */
	@NotBlank(message = "邮箱不能为空", groups = { AddGroup.class, UpdateGroup.class })
	@Email(message = "邮箱格式不正确", groups = { AddGroup.class, UpdateGroup.class })
	private String email;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 状态 0：未禁用 1：禁用
	 */
	private Byte status;
	/**
	 * 用户分类 0：系统用户 1：企业用户
	 */
	private Byte type;
	/**
	 * 是否管理员 0：否 1：是
	 */
	private Byte isAdmin;
	/**
	 * 删除状态 0：未删除 1：删除
	 */
	private Byte isDeleted;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 创建人
	 */
	private String gmtCreater;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 修改人
	 */
	private String gmtReviser;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Byte getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Byte isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Byte getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtCreater() {
		return gmtCreater;
	}
	public void setGmtCreater(String gmtCreater) {
		this.gmtCreater = gmtCreater;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public String getGmtReviser() {
		return gmtReviser;
	}
	public void setGmtReviser(String gmtReviser) {
		this.gmtReviser = gmtReviser;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
