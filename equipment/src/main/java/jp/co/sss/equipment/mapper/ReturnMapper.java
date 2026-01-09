package jp.co.sss.equipment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sss.equipment.dto.DetailListViewDto;

/**
 * 返却マッパー
 */
@Mapper
public interface ReturnMapper {
	//返却画面時に表示させる(貸出中のみ)
	List<DetailListViewDto> returnFind(@Param("name") String name);

//	//返却の更新 stock_master
//	int rentFlagUpdate(List<String> stockCodes);
	
	//返却時の更新　stock_data
	void stockDataUpdate(List<String> stockCodes);
}
