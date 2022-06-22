package com.github.juliocesarscheidt.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import java.io.Serializable;
import java.sql.Timestamp;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder({
  "id",
  "first_name",
  "last_name",
  "email",
  "address",
  "gender",
  "enabled",
  "created_at",
  "updated_at",
  "deleted_at"
})
public class CustomerDTO extends RepresentationModel<CustomerDTO> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Mapping("id")
  @JsonProperty("id")
  private Long uniqueId;

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;

  private String email;

  private String address;

  private String gender;

  private Boolean enabled;

  @JsonProperty("created_at")
  private Timestamp createdAt;

  @JsonProperty("updated_at")
  private Timestamp updatedAt;

  @JsonProperty("deleted_at")
  private Timestamp deletedAt;

  // constructor
  public CustomerDTO() {}

  // getters and setters
  public Long getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(Long uniqueId) {
    this.uniqueId = uniqueId;
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
    int result = super.hashCode();
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((deletedAt == null) ? 0 : deletedAt.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    CustomerDTO other = (CustomerDTO) obj;
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
    if (lastName == null) {
      if (other.lastName != null) return false;
    } else if (!lastName.equals(other.lastName)) return false;
    if (uniqueId == null) {
      if (other.uniqueId != null) return false;
    } else if (!uniqueId.equals(other.uniqueId)) return false;
    if (updatedAt == null) {
      if (other.updatedAt != null) return false;
    } else if (!updatedAt.equals(other.updatedAt)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "CustomerDTO [uniqueId="
        + uniqueId
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

  public Boolean validate() {
    if (this.getGender() != null
        && (!this.getGender().equals("Male") && !this.getGender().equals("Female"))) {
      return false;
    }
    return true;
  }
}
