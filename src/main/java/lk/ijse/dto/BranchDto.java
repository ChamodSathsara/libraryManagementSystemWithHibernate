package lk.ijse.dto;

import lk.ijse.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class BranchDto {
    private int branchId;
    private String branchName;
    private String description;

    public Branch toEntity(){
        Branch branch = new Branch();
        branch.setBranchId(this.branchId);
        branch.setBranchName(this.branchName);
        branch.setDescription(this.description);

        return branch;
    }
}
