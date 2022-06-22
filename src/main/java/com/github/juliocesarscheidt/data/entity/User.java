package com.github.juliocesarscheidt.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"user_name"}))
public class User implements UserDetails, Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_name", length = 255, unique = true)
  private String userName;

  @Column(name = "full_name", length = 255)
  private String fullName;

  @Column(name = "password", length = 255)
  private String password;

  @Column(name = "blocked")
  private Boolean blocked;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "created_at", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date updatedAt;

  @Column(name = "deleted_at", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date deletedAt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_permission",
      joinColumns = {@JoinColumn(name = "id_user")},
      inverseJoinColumns = {@JoinColumn(name = "id_permission")})
  private List<Permission> permissions;

  public List<String> getRoles() {
    List<String> roles = new ArrayList<>();

    for (Permission permission : this.permissions) {
      roles.add(permission.getDescription());
    }
    System.out.println(roles);

    return roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Boolean getBlocked() {
    return blocked;
  }

  public void setBlocked(Boolean blocked) {
    this.blocked = blocked;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }

  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.permissions;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    // not used
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !this.blocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // not used
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.active;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((active == null) ? 0 : active.hashCode());
    result = prime * result + ((blocked == null) ? 0 : blocked.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((deletedAt == null) ? 0 : deletedAt.hashCode());
    result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    User other = (User) obj;
    if (active == null) {
      if (other.active != null) return false;
    } else if (!active.equals(other.active)) return false;
    if (blocked == null) {
      if (other.blocked != null) return false;
    } else if (!blocked.equals(other.blocked)) return false;
    if (createdAt == null) {
      if (other.createdAt != null) return false;
    } else if (!createdAt.equals(other.createdAt)) return false;
    if (deletedAt == null) {
      if (other.deletedAt != null) return false;
    } else if (!deletedAt.equals(other.deletedAt)) return false;
    if (fullName == null) {
      if (other.fullName != null) return false;
    } else if (!fullName.equals(other.fullName)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (password == null) {
      if (other.password != null) return false;
    } else if (!password.equals(other.password)) return false;
    if (permissions == null) {
      if (other.permissions != null) return false;
    } else if (!permissions.equals(other.permissions)) return false;
    if (updatedAt == null) {
      if (other.updatedAt != null) return false;
    } else if (!updatedAt.equals(other.updatedAt)) return false;
    if (userName == null) {
      if (other.userName != null) return false;
    } else if (!userName.equals(other.userName)) return false;
    return true;
  }
}
