package com.gmaurya.ums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stream")
public class Stream {

    @Id
    @Column(name = "streamId", nullable = false)
    private String streamId;

    @Column(name = "streamName", nullable = false)
    private String streamName;

    @Column(name = "duration", nullable = false)
    private int duration;

    public Stream(String streamId, String streamName, int duration) {
        this.streamId = streamId;
        this.streamName = streamName;
        this.duration = duration;
    }
}
