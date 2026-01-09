package jp.co.sss.equipment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.sss.equipment.dto.EquipListViewDto;

/**
 * 
 * @author 小松原 2025/12/08
 */
@Mapper//XMLとの架け橋
public interface IndexMapper {
	//equip_list_view
	List<EquipListViewDto> findAll();//Mapper.XMLで対応するSQL呼び出したデータをListで返す
}
