package lk.ijse.repository;

import lk.ijse.entity.Branch;

import java.util.List;

public interface BranchRepository extends CrudRepository<Integer, Branch>{
    List<Branch> getAllBranch();

    int getPk(String branchName);
}
