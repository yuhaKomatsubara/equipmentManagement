package jp.co.sss.equipment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sss.equipment.dto.DetailListViewDto;
import jp.co.sss.equipment.entity.StaffData;

/**
 * 貸出マッパー
 */
@Mapper
public interface BorrowingMapper {
	//貸出画面時に表示させる(可のみ)
		List<DetailListViewDto> borrowingFind(@Param("name") String name);
		
		//貸出画面に表示させる使用者の取得
		List<StaffData> staffFind();
}
