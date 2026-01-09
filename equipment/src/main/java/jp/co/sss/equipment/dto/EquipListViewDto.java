package jp.co.sss.equipment.dto;
import lombok.Data;

/**
 * 備品一覧画面
 * @author 小松原 2025/12/06
 */
@Data//GeterSetterの省略
public class EquipListViewDto {//DTO 画面表示よう出したい項目をまとめたクラス
	/**名称*/
	private String name;
	
	/**貸出*/
	private Integer lendingCount;

	/**在庫*/
	private Integer stockCount;

	/**不明*/
	private Integer unknownCount;

	/**合計*/
	private Integer total;
}

