package com.claudioneves.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.claudioneves.dynamodb.converter.MusicIdTypeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@DynamoDBTable(tableName = "Music")
@Builder
public class Music implements Serializable {

    public Music(MusicId musicId, String albumTitle, String songYear) {
        this.musicId = musicId;
        this.albumTitle = albumTitle;
        this.songYear = songYear;
    }

    public Music() {
    }

    private static final long serialVersionUID = -4282005207341771716L;

    @Id
    private MusicId musicId;

    // Any other field and their getter/setter + @DynamoDBAttribute annotation
    @DynamoDBHashKey(attributeName = "Artist")
    @DynamoDBAutoGeneratedKey
    public String getArtist() {
        return musicId != null ? musicId.getArtist() : null;
    }

    public void setArtist(String artist) {
        if (musicId == null) {
            musicId = new MusicId();
        }
        musicId.setArtist(artist);
    }

    @DynamoDBRangeKey(attributeName = "SongTitle")
    public String getSongTitle() {
        return musicId != null ? musicId.getSongTitle() : null;
    }

    public void setSongTitle(String songTitle) {
        if (musicId == null) {
            musicId = new MusicId();
        }
        musicId.setSongTitle(songTitle);
    }


    @DynamoDBAttribute(attributeName = "AlbumTitle")
    @JsonProperty("AlbumTitle")
    private String albumTitle;

    @DynamoDBAttribute(attributeName = "SongYear")
    @JsonProperty("SongYear")
    private String songYear;

    @DynamoDBTypeConverted(converter = MusicIdTypeConverter.class)
    public MusicId getMusicId() {
        return musicId;
    }

    public void setMusicId(MusicId musicId) {
        this.musicId = musicId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getSongYear() {
        return songYear;
    }

    public void setSongYear(String songYear) {
        this.songYear = songYear;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", albumTitle='" + albumTitle + '\'' +
                ", songYear='" + songYear + '\'' +
                '}';
    }
}