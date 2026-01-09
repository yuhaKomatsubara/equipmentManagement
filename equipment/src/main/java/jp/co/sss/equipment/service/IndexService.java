package jp.co.sss.equipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.equipment.dto.DetailListViewDto;
import jp.co.sss.equipment.dto.EquipListViewDto;
import jp.co.sss.equipment.mapper.DetailMapper;
import jp.co.sss.equipment.mapper.IndexMapper;
/**
 * 備品一覧サービス
 * @author 小松原
 */
@Service
public class IndexService {
	@Autowired //DIの導入
	IndexMapper indexMapper; //IndexMapperの宣言（newの代わりにここで呼び出す）
	
	@Autowired
	DetailMapper detailMapper;
	
	/**
	 * 備品一覧表示
	 */
	public List<EquipListViewDto> indexFind() {
		return indexMapper.findAll(); //indexMapperのfindAllメソッドを呼び出す(DB情報)	
	}
	
	/**
	 * 詳細画面表示
	 */
	public List<DetailListViewDto> detailFind(String name){
		return detailMapper.detailFind(name);//index.HTMLの検索したい備品をnameとして渡しSQLの検索に使用する
	}
}
