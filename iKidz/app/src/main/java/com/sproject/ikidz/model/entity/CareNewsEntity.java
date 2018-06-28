package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CareNewsEntity {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("title")
@Expose
private String title;
@SerializedName("publish_date")
@Expose
private String publishDate;
@SerializedName("summary")
@Expose
private String summary;
@SerializedName("full_name")
@Expose
private String fullName;
@SerializedName("total_view")
@Expose
private Integer totalView;
@SerializedName("image")
@Expose
private String image;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getPublishDate() {
return publishDate;
}

public void setPublishDate(String publishDate) {
this.publishDate = publishDate;
}

public String getSummary() {
return summary;
}

public void setSummary(String summary) {
this.summary = summary;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public Integer getTotalView() {
return totalView;
}

public void setTotalView(Integer totalView) {
this.totalView = totalView;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

}