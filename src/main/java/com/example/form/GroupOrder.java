package com.example.form;

import javax.validation.GroupSequence;

@GroupSequence({ ValidGroup1.class, ValidGroup2.class})
//バリデーションの順番を設定する、左から順番に
public interface GroupOrder {
	//パブリック変数、インターフェースのGroupOrder

}
