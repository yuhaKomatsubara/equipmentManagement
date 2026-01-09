package jp.co.sss.equipment.entity;

import lombok.Data;

/**
*備品一覧
*@author 小松原　2025/12/04
*DBとの連携に使うもの
 *DBと同じカラムを入れるその際にキャメルケースに変換する
*/
@Data //getter setterの省略でLombokを使用
public class StockMaster {

	/*シリアルナンバー*/
	private String stockCode;

	/*備品名称*/
	private String name;

	/*型番*/
	private String model;

	/*メーカー*/
	private String maker;

	/*分類コード*/
	private Integer stockType;

	/*貸出可否*/
	private Boolean rentFlg;

	/*備考*/
	private String remarks;

	/*削除フラグ*/
	private Boolean del;

}
