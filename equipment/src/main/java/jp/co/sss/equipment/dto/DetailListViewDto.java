package jp.co.sss.equipment.dto;

import lombok.Data;

@Data
public class DetailListViewDto {
	/**備品名*/
	private String equipmentName;
	
	/**シリアルナンバー*/
	private String parentStockCode;
	
	/**使用者*/
	private String staffName;
	
	/**貸出可否*/
	private String rentFlg;
	
	/**貸出開始日*/
	private String startDate;
	
	/**返却予定日*/
	private String limitDate;
	
	/**最終所在確認*/
	private String confirmedDate;
	
	/**備考*/
	private String remarks;
	
	/**シーケンスid*/
	private Integer equipmentId;
	
	/**スタッフid*/
	private Integer staffNo;
}
