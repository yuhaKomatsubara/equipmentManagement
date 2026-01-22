package jp.co.sss.equipment.controller;

import java.util.List;
import java.util.Map;

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
		DetailListViewDto detailName = detailNameList.get(0);
		; //備品名の取得
		model.addAttribute("detailName", detailName); //画面に渡す

		List<DetailListViewDto> borrowingList = borrowingService.borrowingFindView(name);
		List<StaffData> staffList = borrowingService.staffDataFind();

		model.addAttribute("itemDetail", borrowingList); //貸出リストの取得
		model.addAttribute("staffName", staffList); //使用者名の取得
		model.addAttribute("today", DateUtil.getToday().toString()); //今日の日付
		model.addAttribute("defaultLimit", DateUtil.getDefaultLimitDate().toString()); //デフォルト返却予定日
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
			@RequestParam(value = "equipmentIdList", required = false) List<Integer> equipmentIdList,
			@RequestParam Map<String, String> serialMap,
			@RequestParam Map<String, String> staffNoMap,
			@RequestParam Map<String, String> startDateMap,
			@RequestParam Map<String, String> limitDateMap,
			@RequestParam String name,
			RedirectAttributes redirectAttributes) {

		//サービス層で入力チェック(入力メッセージをリストで受け取る)
		List<String> errorMessages = borrowingService.validateBorrowing(
				equipmentIdList,
				serialMap,
				staffNoMap,
				startDateMap,
				limitDateMap);

		//エラーメッセージがある場合、リダイレクトしてメッセージを表示
		if (!errorMessages.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
			redirectAttributes.addAttribute("name", name);
			return "redirect:/borrowingView";
		}

		//貸出更新処理
		try {
			borrowingService.borrowingEquipment(
					equipmentIdList,
					staffNoMap,
					startDateMap,
					limitDateMap);
			redirectAttributes.addFlashAttribute("updateMessage", "貸出処理が完了しました。");
			
			//貸出不可の場合の例外処理
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessages", List.of("他のブラウザで更新されました。貸出処理は行えませんでした。"));
			redirectAttributes.addAttribute("name", name);
			return "redirect:/borrowingView";
		}

		redirectAttributes.addAttribute("name", name);
		return "redirect:/borrowingView";
	}
}