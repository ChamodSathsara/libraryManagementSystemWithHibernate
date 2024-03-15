package lk.ijse.repository.impl;

import lk.ijse.entity.Branch;
import lk.ijse.repository.BranchRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BranchRepositoryImpl implements BranchRepository {
    private static BranchRepository branchRepository;

    private Session session;

    private BranchRepositoryImpl(){}

    public static BranchRepository getInstance(){
        return branchRepository == null ? branchRepository = new BranchRepositoryImpl() : branchRepository;
    }
    @Override
    public List<Branch> getAllBranch() {
        String sql = "SELECT  c FROM Branch AS c";
        Query query = session.createQuery(sql);
        List<Branch> list = query.list();
        System.out.println("get All athule");
        for (Branch branch : list){
            System.out.println(branch);
        }

        return list;
    }

    @Override
    public int getPk(String branchName) {
        String sql = "SELECT c.branchId FROM Branch AS c WHERE c.branchName = :branchName";
        Query query = session.createQuery(sql,Integer.class);
        query.setParameter("branchName", branchName);
        List<Integer> list = query.getResultList();
        int pk = list.get(0);

        return pk;
    }

    @Override
    public Integer save(Branch branch) {
        session.save(branch);
        return 1;
    }

    @Override
    public void update(Branch branch) {
        session.update(branch);
    }

    @Override
    public Branch get(Integer integer) {
        return session.get(Branch.class,integer);
    }

    @Override
    public void delete(Branch branch) {
        session.delete(branch);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
