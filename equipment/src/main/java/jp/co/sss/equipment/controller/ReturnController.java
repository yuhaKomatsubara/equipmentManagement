package jp.co.sss.equipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

		//デバッグ
		int num = 0;
		for (DetailListViewDto i : detailName) {
			num++;
			System.out.println("===========" + num + "===========");
			System.out.println("===========" + "返却" + "===========");
			System.out.println("備品名:" + i.getEquipmentName());
			System.out.println("シリアル:" + i.getParentStockCode());
			System.out.println("使用者:" + i.getStaffName());
			System.out.println("貸出可否:" + i.getRentFlg());
			System.out.println("貸出開始日:" + i.getStartDate());
			System.out.println("返却予定日:" + i.getLimitDate());
			System.out.println("最終所在確認" + i.getConfirmedDate());
			System.out.println("備考:" + i.getRemarks());
			System.out.println("シーケンスID: " + i.getEquipmentId());
			System.out.println("スタッフID:" + i.getStaffNo());
		}
		return "return/returnView"; //templatesフォルダーのhtmlを表示させる
	}

	/**
	 * 返却処理
	 */
	@PostMapping("/returnProcess")
	public String returnProcess(
			@RequestParam(value = "equipmentIdList", required = false) List<Integer> equipmentIdList, //Listにidが格納されている
			@RequestParam(value = "name", required = false) String name,
			RedirectAttributes redirectAttributes) {
		if (equipmentIdList != null && !equipmentIdList.isEmpty()) { //チェックが入っている場合
			returnService.returnEquipment(equipmentIdList); //サービス層でsqlのマッパー呼び出し
		}
		List<DetailListViewDto> detailNameList = indexService.detailFind(name);
		if (!detailNameList.isEmpty()) { //備品名の取得
			redirectAttributes.addFlashAttribute("detailName", detailNameList.get(0));
		}
		redirectAttributes.addAttribute("name", name);
	    return "redirect:/returnView";
	}
}