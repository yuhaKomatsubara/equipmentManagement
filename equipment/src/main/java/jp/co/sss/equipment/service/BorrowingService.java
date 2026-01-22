package jp.co.sss.equipment.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sss.equipment.dto.DetailListViewDto;
import jp.co.sss.equipment.entity.StaffData;
import jp.co.sss.equipment.mapper.BorrowingMapper;
import jp.co.sss.equipment.util.DateUtil;

/**
 *備品管理「貸出」サービス
 * @author 小松原
 */
@Service
public class BorrowingService {
	@Autowired
	BorrowingMapper borrowingMapper;

	/**
	 * 貸出画面に現在貸出可能（手元にある）の備品を渡す
	 * @param name
	 * @return ReturnController
	 */
	public List<DetailListViewDto> borrowingFindView(String name) {
		return borrowingMapper.borrowingFind(name); //マッパーの処理を返す
	}

	/**
	 * スタッフデータを取得
	 * @return
	 */
	public List<StaffData> staffDataFind() {
		return borrowingMapper.staffFind();//マッパーの処理を返す
	}

	/**
	 * 入力チェック
	 * @param equipmentIdList
	 * @param serialMap
	 * @param staffNoMap
	 * @param startDateMap
	 * @param limitDateMap
	 * @return
	 */
	public List<String> validateBorrowing(
			List<Integer> equipmentIdList,
			Map<String, String> serialMap,
			Map<String, String> staffNoMap,
			Map<String, String> startDateMap,
			Map<String, String> limitDateMap) {

		List<String> errorMessages = new ArrayList<>();

		if (equipmentIdList == null || equipmentIdList.isEmpty()) {
			errorMessages.add("貸出対象が選択されていません");
			return errorMessages;
		}

		for (Integer id : equipmentIdList) {
			List<String> rowErrors = new ArrayList<>();
			// 画面のこの行(id)に対応する入力値を取得するためのキーを作成
			String serial = serialMap.get("serialMap[" + id + "]"); //シリアル番号取得
			String staffStr = staffNoMap.get("staffNoMap[" + id + "]");//使用者取得
			String startStr = startDateMap.get("startDateMap[" + id + "]");//貸出開始日取得
			String limitStr = limitDateMap.get("limitDateMap[" + id + "]");//返却予定日取得

			//入力チェック
			if (staffStr == null || staffStr.isBlank()) {
				rowErrors.add("使用者未選択");
			}
			if (startStr == null || startStr.isBlank()) {
				rowErrors.add("貸出開始日未入力");
			}
			if (limitStr == null || limitStr.isBlank()) {
				rowErrors.add("返却予定日未入力");
			}
			if (!rowErrors.isEmpty()) {
				errorMessages.add("【シリアル：" + serial + "】");
				errorMessages.addAll(rowErrors);
				continue;
			}
			
			LocalDate today = DateUtil.getToday();
			LocalDate startDate = LocalDate.parse(startStr);
			LocalDate limitDate = LocalDate.parse(limitStr);

			if (limitDate.isBefore(today)) {
				errorMessages.add("【シリアル：" + serial + "】返却予定日が本日より前です");
			} else if (limitDate.isBefore(startDate)) {
				errorMessages.add("【シリアル：" + serial + "】返却予定日が貸出開始日より前です");
			}
		}
		return errorMessages;
	}

	/**
	 * 貸出更新
	 */
	@Transactional
	public void borrowingEquipment(
			List<Integer> equipmentIdList, //更新対象の備品id
			Map<String, String> staffNoMap, //備品idとスタッフ名
			Map<String, String> startDateMap, //備品idと貸出日
			Map<String, String> limitDateMap //備品idと返却期限
	) {
		for (Integer id : equipmentIdList) { //チェックされた数だけ１件ずつ処理を行う　

			// 画面のこの行(id)に対応する入力値を取得するためのキーを作る
			String staffKey = "staffNoMap[" + id + "]";
			String startKey = "startDateMap[" + id + "]";
			String limitKey = "limitDateMap[" + id + "]";

			// 備品IDに対応する入力値をMapから取得
			String staffStr = staffNoMap.get(staffKey);
			String startStr = startDateMap.get(startKey);
			String limitStr = limitDateMap.get(limitKey);

			//未入力がある場合弾く
			if (staffStr == null || staffStr.isBlank()
					|| startStr == null || startStr.isBlank()
					|| limitStr == null || limitStr.isBlank()) {
				continue;
			}

			//文字列からDate形に型変換
			LocalDate start = LocalDate.parse(startStr);
			LocalDate limit = LocalDate.parse(limitStr);

			//貸出日と返却日を比べて返却日が貸出日より前にならないかチェック
			if (!start.isAfter(limit)) {
				int updateCount = borrowingMapper.borrowingUpdate(
						id,
						Integer.valueOf(staffStr),
						start,
						limit);
				if (updateCount == 0) {
					throw new IllegalStateException("他のブラウザで更新済み");
				}
			}
		}
	}
}