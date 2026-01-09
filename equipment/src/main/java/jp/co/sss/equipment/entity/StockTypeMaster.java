package jp.co.sss.equipment.entity;

import lombok.Data;

/**
 *備品分類
 *@author 小松原　2025/12/04
 *DBとの連携に使うもの
 *DBと同じカラムを入れるその際にキャメルケースに変換する
 */
@Data //getter setterの省略でLombokを使用
public class StockTypeMaster {
	/*採番*/
	private Integer id;

	/*分類コード*/
	private Integer stockType;

	/*分類名称*/
	private String name;

	/*削除フラグ*/
	private Boolean del;

}