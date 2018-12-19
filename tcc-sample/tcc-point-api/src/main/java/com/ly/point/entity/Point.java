package com.ly.point.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_point")
public class Point {
    @Id
    private Long userId;
    private Long value;
}
