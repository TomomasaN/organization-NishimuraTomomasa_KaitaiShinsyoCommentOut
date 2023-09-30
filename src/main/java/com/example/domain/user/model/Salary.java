package com.example.domain.user.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
//クラス内の変数に対してgetter,setterでアクセスすることが可能になる
@Entity
@Table(name="t_salary")

public class Salary {
//private String userId;
//private String yearMonth;
@EmbeddedId
//主キークラスを使う
private SalaryKey salaryKey;
private Integer salary;
}
