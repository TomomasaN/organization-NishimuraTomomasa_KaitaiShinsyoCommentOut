package com.example.domain.user.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;



@Data
//クラス内の変数に対してgetter,setterでアクセスすることが可能になる
@Entity
//クラス名と同じテーブル名を生成してくれる
@Table(name="m_user")
//クラス名とテーブル名が異なる場合つけるアノテーション。アノテーションのname属性にマッピングしたいテーブル名を設定する
public class MUser {
	@Id
	//このフィールドが主キーであることをJPAが認識してくれる
private String userId;
private String password;
private String UserName;
private Date birthday;
private Integer age;
private Integer gender;
private Integer departmentId;
private String role;

@ManyToOne(optional = true)
//ユーザーマスタが多、部署マスタが一の関係

@JoinColumn(insertable=false, updatable=false, name = "departmentId")
//結合するカラムを指定する場合に使う。一対一の関係のテーブル結合にも使用する

private Department department;

@OneToMany
//一対多の関係を表す
@JoinColumn(insertable=false, updatable=false, name = "userId")
//結合するカラムを指定する場合に使う。一対一の関係のテーブル結合にも使用する

private List<Salary> salaryList;
}
