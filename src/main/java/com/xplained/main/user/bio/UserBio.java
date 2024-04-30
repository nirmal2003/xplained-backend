package com.xplained.main.user.bio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserBio {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String description;
    private String image;
    private String heading;

    private String linkedin;
    private String youtube;
    private String web;
    private String otherUrl;
}
