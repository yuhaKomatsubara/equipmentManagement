package jp.co.sss.equipment.entity;

import java.time.LocalDate;

/**
 *備品貸出データ
 *@author 小松原　2025/12/04
 */
import lombok.Data;
/**
 *機材詳細
 *@author 小松原　2025/12/04
 *DBとの連携に使うもの
 *DBと同じカラムを入れるその際にキャメルケースに変換する
 */
@Data //getter setterの省略でLombokを使用
public class StockData {
	/*採番*/
	private Integer id;

	/*シリアルナンバー*/
	private String stockCode;

	/*親シリアルナンバー*/
	private String parentStockCode;

	/*貸出先ユーザー*/
	private Integer staffNo;

	/*貸出開始日*/
	private LocalDate startDate;

	/*返却予定日*/
	private LocalDate limitDate;

	/*返却日*/
	private LocalDate returnDate;

	/*最終所在確認日*/
	private LocalDate confirmedDate;
	
	/*削除フラグ*/
	private Boolean del;
}