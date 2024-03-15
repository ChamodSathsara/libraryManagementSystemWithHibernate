package lk.ijse.service;

import lk.ijse.dto.BranchDto;

import java.util.List;

public interface BranchService {
    boolean saveBranch(BranchDto branchDto);
    BranchDto getBranch(int id);
    boolean updateBranch(BranchDto branchDto);
    boolean deleteBranch(BranchDto branchDto);

    List<BranchDto> getAllBranch();

    int getPk(String branchName);
}
