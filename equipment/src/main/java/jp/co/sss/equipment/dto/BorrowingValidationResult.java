package jp.co.sss.equipment.dto;

import java.util.List;
import java.util.Set;

public class BorrowingValidationResult {

    private List<String> errorMessages;
    private Set<Integer> errorEquipmentIds;
    private Set<Integer> warningEquipmentIds;
    private Set<Integer> normalEquipmentIds; 

    public BorrowingValidationResult(
        List<String> errorMessages,
        Set<Integer> errorEquipmentIds,
        Set<Integer> warningEquipmentIds,
        Set<Integer> normalEquipmentIds
    ) {
        this.errorMessages = errorMessages;
        this.errorEquipmentIds = errorEquipmentIds;
        this.warningEquipmentIds = warningEquipmentIds;
        this.normalEquipmentIds = normalEquipmentIds;
    }


    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public Set<Integer> getErrorEquipmentIds() {
        return errorEquipmentIds;
    }

    public Set<Integer> getWarningEquipmentIds() {
        return warningEquipmentIds;
    }

    public Set<Integer> getNormalEquipmentIds() {
        return normalEquipmentIds;
    }
}
