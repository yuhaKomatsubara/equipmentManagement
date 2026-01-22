package jp.co.sss.equipment.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sss.equipment.dto.DetailListViewDto;
import jp.co.sss.equipment.entity.StaffData;
import jp.co.sss.equipment.entity.StockData;

/**
 * 貸出マッパー
 */
@Mapper
public interface BorrowingMapper {
	/*
	 * 貸出画面時に表示させる(可のみ)
	 */
	List<DetailListViewDto> borrowingFind(@Param("name") String name);

	/*
	 * 貸出画面に表示させる使用者の取得
	 */
	List<StaffData> staffFind();

	/**
	 * 最新情報の取得
	 */
	StockData findById(Integer id);

	/**
	 * 貸出更新
	 */
	int borrowingUpdate( //サービスから渡された値をxmlに渡す サービス層で一軒ずつ回している
			@Param("id") Integer id,
			@Param("staffNo") Integer staffNo,
			@Param("startDate") LocalDate startDate,
			@Param("limitDate") LocalDate limitDate);
}
