package jp.co.sss.equipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.equipment.dto.DetailListViewDto;
import jp.co.sss.equipment.service.IndexService;
import jp.co.sss.equipment.service.ReturnService;

/**
 *備品管理「返却」
 * @author 小松原
 */
@Controller
public class ReturnController {
	@Autowired
	ReturnService returnService;

	@Autowired
	IndexService indexService;

	/**
	 * 返却画面
	 * @author 小松原
	 * @param model
	 * @param name
	 * @return　templates/index/returnViw 返却画面
	 */
	@GetMapping("/returnView")
	public String returnView(Model model, String name) {
		List<DetailListViewDto> returnList = returnService.returnFindView(name); //備品名を取得する　サービス層で処理
		List<DetailListViewDto> detailName = indexService.detailFind(name);//貸出中の備品を取得する
		model.addAttribute("detailName", detailName.get(0));//備品名をひとつ取得し、HTMLに表示させる
		model.addAttribute("itemDetail", returnList);//貸出中の備品をHTMLのテーブルに表示させる
		return "return/returnView"; //templatesフォルダーのhtmlを表示させる
	}

	/**
	 * 返却処理
	 */
	@PostMapping("/returnProcess")
	public String returnProcess(
			@RequestParam("equipmentIdList") List<Integer> equipmentIdList,//Listにシリアルナンバーが格納されている
			@RequestParam("name") String name,
			Model model) {
		if(!equipmentIdList.isEmpty()) {
		returnService.returnEquipment(equipmentIdList);
		}
		System.out.println("equipmentIdList = " + equipmentIdList);
		List<DetailListViewDto> returnViewList = returnService.returnFindView(name); //備品名を取得する　サービス層で処理
		List<DetailListViewDto> detailName = indexService.detailFind(name);//貸出中の備品を取得する
		model.addAttribute("detailName", detailName.get(0));//備品名をひとつ取得し、HTMLに表示させる
		model.addAttribute("itemDetail", returnViewList);//貸出中の備品をHTMLのテーブルに表示させる
		return "return/returnView";
	}
}