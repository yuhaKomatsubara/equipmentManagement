package jp.co.sss.equipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.equipment.dto.DetailListViewDto;
import jp.co.sss.equipment.entity.StaffData;
import jp.co.sss.equipment.service.BorrowingService;
import jp.co.sss.equipment.service.IndexService;

/**
 *備品管理「貸出」
 * @author 小松原
 */
@Controller
public class BorrowingController {
	@Autowired //DIの導入
	BorrowingService borrowingService;

	@Autowired
	IndexService indexService;
	/**
	 * 貸出画面
	 * @author 小松原
	 * @param model
	 * @param name
	 * @return　templates/index/returnView 貸出画面
	 */
	@GetMapping("borrowingView")
	public String borrowingView(Model model, String name) {
		List<DetailListViewDto> borrowingList = borrowingService.borrowingFindView(name); //備品名を取得する　サービス層で処理
		List<DetailListViewDto> detailName = indexService.detailFind(name);//貸出中の備品を取得する
		List<StaffData> staffList = borrowingService.staffDataFind(); //スタッフ情報を取得する
			model.addAttribute("detailName", detailName.get(0)); //備品名をひとつ取得し、HTMLに表示させる
			model.addAttribute("itemDetail", borrowingList);//貸出中の備品をHTMLのテーブルに表示させる
			model.addAttribute("staffName",staffList);//スタッフ情報をhtmlのドロップダウンに表示させる
			return "borrowing/borrowingView";//templatesフォルダーのhtmlを表示させる
	}
}