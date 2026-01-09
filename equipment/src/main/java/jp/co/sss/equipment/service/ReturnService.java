package jp.co.sss.equipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.equipment.dto.DetailListViewDto;
import jp.co.sss.equipment.mapper.ReturnMapper;

/**
 *備品管理「返却」
 * @author 小松原
 */
@Service
public class ReturnService {
	@Autowired //DIの導入
	ReturnMapper returnMapper; //DetailMapperの宣言（newの代わりにここで呼び出す）
	/**
	 * 返却画面に現在貸出中の備品を渡す
	 * @param name
	 * @return ReturnController
	 */
	public List<DetailListViewDto> returnFindView(String name){
		return returnMapper.returnFind(name);//マッパーの処理を返す
	}
	
	/**
	 * 返却時の処理（貸出可否を１に変更）
	 */
	public void returnEquipment(List<String> returnList) {
		//returnMapper.rentFlagUpdate(returnList);
		returnMapper.stockDataUpdate(returnList);
	}
}
