package lk.ijse.entity;

import lk.ijse.dto.BranchDto;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER , mappedBy = "branch")
    private List<Book> bookList = new ArrayList<>();

    public Branch() {
    }

    public Branch(int branchId, String branchName, String description) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.description = description;
    }

    public BranchDto toDto(){
        BranchDto branchDto = new BranchDto();
        branchDto.setBranchId(this.branchId);
        branchDto.setBranchName(this.branchName);
        branchDto.setDescription(this.description);

        return branchDto;
    }

}
