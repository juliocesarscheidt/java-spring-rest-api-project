package com.github.juliocesarscheidt.data.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Customer implements Serializable {

  private static final long serialVersionUID = 1L;

  // data mapping
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @Column(nullable = false, length = 255, unique = true)
  private String email;

  @Column(nullable = true, length = 255)
  private String address;

  @Column(nullable = true, length = 50)
  private String gender;

  @Column(nullable = false)
  private Boolean enabled;

  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at", nullable = true)
  private Timestamp updatedAt;

  @Column(name = "deleted_at", nullable = true)
  private Timestamp deletedAt;

  // constructor
  public Customer() {}

  // getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Timestamp getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((deletedAt == null) ? 0 : deletedAt.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Customer other = (Customer) obj;
    if (address == null) {
      if (other.address != null) return false;
    } else if (!address.equals(other.address)) return false;
    if (createdAt == null) {
      if (other.createdAt != null) return false;
    } else if (!createdAt.equals(other.createdAt)) return false;
    if (deletedAt == null) {
      if (other.deletedAt != null) return false;
    } else if (!deletedAt.equals(other.deletedAt)) return false;
    if (email == null) {
      if (other.email != null) return false;
    } else if (!email.equals(other.email)) return false;
    if (enabled == null) {
      if (other.enabled != null) return false;
    } else if (!enabled.equals(other.enabled)) return false;
    if (firstName == null) {
      if (other.firstName != null) return false;
    } else if (!firstName.equals(other.firstName)) return false;
    if (gender == null) {
      if (other.gender != null) return false;
    } else if (!gender.equals(other.gender)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (lastName == null) {
      if (other.lastName != null) return false;
    } else if (!lastName.equals(other.lastName)) return false;
    if (updatedAt == null) {
      if (other.updatedAt != null) return false;
    } else if (!updatedAt.equals(other.updatedAt)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Customer [id="
        + id
        + ", firstName="
        + firstName
        + ", lastName="
        + lastName
        + ", email="
        + email
        + ", address="
        + address
        + ", gender="
        + gender
        + ", enabled="
        + enabled
        + ", createdAt="
        + createdAt
        + ", updatedAt="
        + updatedAt
        + ", deletedAt="
        + deletedAt
        + "]";
  }
}
