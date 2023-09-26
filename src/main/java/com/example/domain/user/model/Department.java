package com.example.domain.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
//クラス内の変数に対してgetter,setterでアクセスすることが可能になる
@Entity
//Entityクラスにする
@Table(name="m_department")

public class Department {
@Id
private Integer departmentId;
private String departmentName;
}
