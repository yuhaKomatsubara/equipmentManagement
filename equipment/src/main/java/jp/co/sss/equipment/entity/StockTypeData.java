package jp.co.sss.equipment.entity;

import lombok.Data;

/**
 *備品の分類と権限
 *@author 小松原　2025/12/04
 *DBとの連携に使うもの
 *DBと同じカラムを入れるその際にキャメルケースに変換する
 */
@Data //getter setterの省略でLombokを使用
public class StockTypeData {
	/*採番*/
	private Integer id;

	/*分類コード*/
	private Integer stockType;

	/*権限*/
	private Integer authNo;

	/*読み込み可否*/
	private Boolean readFlg;

	/*書き込み可否*/
	private Boolean writeFlg;

	/*削除可否*/
	private Boolean delFlg;

	/*削除フラグ*/
	private Boolean del;
}

