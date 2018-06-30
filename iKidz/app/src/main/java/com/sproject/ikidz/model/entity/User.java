package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/8/2018
 * Time: 11:29 PM
 * Email: vihahb@gmail.com
 */
public class User extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("role_id")
    @Expose
    private String roleId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("base_url")
    @Expose
    private String base_url;
    @SerializedName("token_firebase")
    @Expose
    private String tokenFirebase;
    @SerializedName("school_code")
    @Expose
    private String schoolCode;
    @SerializedName("teachers")
    @Expose
    private Teachers teachers;
    @SerializedName("parents")
    @Expose
    private Parents parents;
    @SerializedName("roles")
    @Expose
    private Roles roles;

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param id
     * @param parents
     * @param username
     * @param email
     * @param roles
     * @param base_url
     * @param tokenFirebase
     * @param schoolCode
     * @param fullName
     * @param avatar
     * @param teachers
     * @param roleId
     * @param userType
     */

    public User(Integer id, String username, String userType, String avatar, String roleId, String email, String fullName, String base_url, String tokenFirebase, String schoolCode, Teachers teachers, Parents parents, Roles roles) {
        this.id = id;
        this.username = username;
        this.userType = userType;
        this.avatar = avatar;
        this.roleId = roleId;
        this.email = email;
        this.fullName = fullName;
        this.base_url = base_url;
        this.tokenFirebase = tokenFirebase;
        this.schoolCode = schoolCode;
        this.teachers = teachers;
        this.parents = parents;
        this.roles = roles;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTokenFirebase() {
        return tokenFirebase;
    }

    public void setTokenFirebase(String tokenFirebase) {
        this.tokenFirebase = tokenFirebase;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userType='" + userType + '\'' +
                ", avatar='" + avatar + '\'' +
                ", roleId='" + roleId + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", base_url='" + base_url + '\'' +
                ", tokenFirebase='" + tokenFirebase + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", teachers=" + teachers +
                ", parents=" + parents +
                ", roles=" + roles +
                '}';
    }
}
