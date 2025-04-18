package com.minidevtechcom.myanHomeLabBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private int movieId;
    private String moiveName;

    @Id
    private int tmdbId;
    private String sharedLink;
    private String sharedProviderName;
    private String fileSize;
    private String resolutionDesc;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date linkUploadDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date linkUpdateDate;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getSharedLink() {
        return sharedLink;
    }

    public void setSharedLink(String sharedLink) {
        this.sharedLink = sharedLink;
    }

    public String getSharedProviderName() {
        return sharedProviderName;
    }

    public void setSharedProviderName(String sharedProviderName) {
        this.sharedProviderName = sharedProviderName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getResolutionDesc() {
        return resolutionDesc;
    }

    public void setResolutionDesc(String resolutionDesc) {
        this.resolutionDesc = resolutionDesc;
    }

    public Date getLinkUploadDate() {
        return linkUploadDate;
    }

    public void setLinkUploadDate(Date linkUploadDate) {
        this.linkUploadDate = linkUploadDate;
    }

    public Date getLinkUpdateDate() {
        return linkUpdateDate;
    }

    public void setLinkUpdateDate(Date linkUpdateDate) {
        this.linkUpdateDate = linkUpdateDate;
    }
}
