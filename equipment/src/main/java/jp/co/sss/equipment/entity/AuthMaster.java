package jp.co.sss.equipment.entity;

import lombok.Data;

/**
 *権限
 *@author 小松原　2025/12/04
 *DBとの連携に使うもの
 *DBと同じカラムを入れるその際にキャメルケースに変換する
 */
@Data //getter setterの省略でLombokを使用
public class AuthMaster {
	/*権限*/
	private Integer authNo;

	/*権限の名称*/
	private String authName;

	/*備考*/
	private String remarks;

	/*削除フラグ*/
	private Boolean del;
}
