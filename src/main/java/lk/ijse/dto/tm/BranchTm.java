package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class BranchTm {
    private int branchId;
    private String branchName;
    private int count;
    private String description;
    private Button visitBooks;

    public BranchTm(int branchId , String branchName , String description){
        this.branchId = branchId;
        this.branchName = branchName;
        this.description = description;
    }
}
