package com.github.juliocesarscheidt.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder({
  "id",
  "author",
  "launch_date",
  "price",
  "title",
  "created_at",
  "updated_at",
  "deleted_at"
})
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Mapping("id")
  @JsonProperty("id")
  private Long uniqueId;

  private String author;

  @JsonProperty("launch_date")
  private Date launchDate;

  private Double price;

  private String title;

  @JsonProperty("created_at")
  private Timestamp createdAt;

  @JsonProperty("updated_at")
  private Timestamp updatedAt;

  @JsonProperty("deleted_at")
  private Timestamp deletedAt;

  // constructor
  public BookDTO() {}

  public Long getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(Long uniqueId) {
    this.uniqueId = uniqueId;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getLaunchDate() {
    return launchDate;
  }

  public void setLaunchDate(Date launchDate) {
    this.launchDate = launchDate;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((launchDate == null) ? 0 : launchDate.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    BookDTO other = (BookDTO) obj;
    if (author == null) {
      if (other.author != null) return false;
    } else if (!author.equals(other.author)) return false;
    if (launchDate == null) {
      if (other.launchDate != null) return false;
    } else if (!launchDate.equals(other.launchDate)) return false;
    if (price == null) {
      if (other.price != null) return false;
    } else if (!price.equals(other.price)) return false;
    if (title == null) {
      if (other.title != null) return false;
    } else if (!title.equals(other.title)) return false;
    if (uniqueId == null) {
      if (other.uniqueId != null) return false;
    } else if (!uniqueId.equals(other.uniqueId)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "BookDTO [uniqueId="
        + uniqueId
        + ", author="
        + author
        + ", launchDate="
        + launchDate
        + ", price="
        + price
        + ", title="
        + title
        + ", createdAt="
        + createdAt
        + ", updatedAt="
        + updatedAt
        + ", deletedAt="
        + deletedAt
        + "]";
  }

  public Boolean validate() {
    return true;
  }
}
