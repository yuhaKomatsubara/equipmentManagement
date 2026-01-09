package jp.co.sss.equipment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sss.equipment.dto.DetailListViewDto;

@Mapper
public interface DetailMapper {
	//equip_detail_view
	//詳細の表示
	List<DetailListViewDto> detailFind(@Param("name") String name);//Mapper.XMLで対応するSQL呼び出したデータをListで返す
}
