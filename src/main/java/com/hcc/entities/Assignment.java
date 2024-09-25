package com.hcc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "assignments")
public class Assignment {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private Integer number;
    private String githubUrl;
    private String branch;
    private String reviewVideoUrl;
    private User lmsUser;

    public Assignment(){}

    public Assignment(String status, Integer number, String githubUrl, String branch, String reviewVideoUrl, User lmsUser) {
        this.status = status;
        this.number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.lmsUser = lmsUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getReviewVideoUrl() {
        return reviewVideoUrl;
    }

    public void setReviewVideoUrl(String reviewVideoUrl) {
        this.reviewVideoUrl = reviewVideoUrl;
    }

    public User getLmsUser() {
        return lmsUser;
    }

    public void setLmsUser(User lmsUser) {
        this.lmsUser = lmsUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(number, that.number) && Objects.equals(githubUrl, that.githubUrl) && Objects.equals(branch, that.branch) && Objects.equals(reviewVideoUrl, that.reviewVideoUrl) && Objects.equals(lmsUser, that.lmsUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, number, githubUrl, branch, reviewVideoUrl, lmsUser);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "status='" + status + '\'' +
                ", number=" + number +
                ", githubUrl='" + githubUrl + '\'' +
                ", branch='" + branch + '\'' +
                ", reviewVideoUrl='" + reviewVideoUrl + '\'' +
                ", user=" + lmsUser +
                '}';
    }
}
