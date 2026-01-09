package jp.co.sss.equipment.entity;

import lombok.Data;

/**
 *社員データ
 *@author 小松原　2025/12/04
 *DBとの連携に使うもの
 *DBと同じカラムを入れるその際にキャメルケースに変換する
 */
@Data //getter setterの省略でLombokを使用
public class StaffData {
	/*社員番号*/
	private Integer staffNo;

	/*氏名*/
	private String name;

	/*権限*/
	private Integer authNo;

	/*削除フラグ*/
	private Boolean del;
}
