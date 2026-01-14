package jp.co.sss.equipment.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sss.equipment.dto.DetailListViewDto;
import jp.co.sss.equipment.entity.StaffData;
import jp.co.sss.equipment.service.BorrowingService;
import jp.co.sss.equipment.service.IndexService;
import jp.co.sss.equipment.util.DateUtil;

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
	@GetMapping("/borrowingView")
	public String borrowingView(Model model, String name) {
		List<DetailListViewDto> detailNameList = indexService.detailFind(name);
		DetailListViewDto detailName = detailNameList.get(0);;  //備品名の取得
		model.addAttribute("detailName", detailName); //画面に渡す

		List<DetailListViewDto> borrowingList = borrowingService.borrowingFindView(name);
		List<StaffData> staffList = borrowingService.staffDataFind();

		model.addAttribute("itemDetail", borrowingList); //貸出リストの取得
		model.addAttribute("staffName", staffList); //使用者名の取得
		model.addAttribute("today", DateUtil.getToday().toString()); //今日の日付
		model.addAttribute("defaultLimit", DateUtil.getDefaultLimitDate().toString()); //デフォルト返却予定日

		//シーケンスidが取得できていない
		//デバッグ
		int num = 0;
		for (DetailListViewDto i : detailNameList) {
			num++;
			System.out.println("===========" + num + "===========");	
			System.out.println("===========" + "貸出" + "===========");
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
		return "borrowing/borrowingView";
	}

	/**
	 * 貸出処理
	 * @param equipmentIdList
	 * @param name
	 * @param model
	 * @return
	 */
	@PostMapping("/borrowingProcess")
	public String borrowingProcess(
			//「value」HTTPリクエストパラメータequipmentIdListをメソッドの引数に入れる  リクエストパラメータの名前
			//「required = false」リクエストパラメータが必須ではないことを示す
			@RequestParam(value = "equipmentIdList", required = false) List<Integer> equipmentIdList,
			@RequestParam List<Integer> staffNoList, //使用者リスト
			@RequestParam List<LocalDate> startDateList, //貸出開始日リスト
			@RequestParam List<LocalDate> limitDateList, //返却予定日リスト
			@RequestParam(value = "name", required = false) String name,
			RedirectAttributes redirectAttributes) {

		if (equipmentIdList != null && !equipmentIdList.isEmpty()
			    && staffNoList != null && !staffNoList.isEmpty()
			    && startDateList != null && !startDateList.isEmpty()
			    && limitDateList != null && !limitDateList.isEmpty()) {  //全て入力されている場合のみ次の処理へ

			    borrowingService.borrowingEquipment(
			        equipmentIdList,
			        staffNoList,
			        startDateList,
			        limitDateList);
			}
		List<DetailListViewDto> detailNameList = indexService.detailFind(name);
		if (!detailNameList.isEmpty()) { //備品名の取得
			redirectAttributes.addFlashAttribute("detailName", detailNameList.get(0));
		}
		redirectAttributes.addAttribute("name", name);
	    return "redirect:/borrowingView";
	}
}