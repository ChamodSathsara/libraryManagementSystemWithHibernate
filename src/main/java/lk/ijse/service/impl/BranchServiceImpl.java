package lk.ijse.service.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;
import lk.ijse.repository.BranchRepository;
import lk.ijse.repository.impl.BranchRepositoryImpl;
import lk.ijse.service.BranchService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchServiceImpl implements BranchService {
    private static BranchService branchService;

    private Session session;

    private final BranchRepository branchRepository;

    private BranchServiceImpl(){
        branchRepository = BranchRepositoryImpl.getInstance();
    }
    public static BranchService getInstance(){
        return branchService == null ? branchService = new BranchServiceImpl() : branchService;
    }
    @Override
    public boolean saveBranch(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            int x = branchRepository.save(branchDto.toEntity());
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public BranchDto getBranch(int id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            Branch branch = branchRepository.get(id);
            return branch.toDto();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateBranch(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            branchRepository.update(branchDto.toEntity());
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteBranch(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            branchRepository.delete(branchDto.toEntity());
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<BranchDto> getAllBranch() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            branchRepository.setSession(session);
            List<Branch> branchList = branchRepository.getAllBranch();
            List<BranchDto> dtoList =new ArrayList<>();
            for (Branch branch : branchList){
                dtoList.add(branch.toDto());
            }
            return dtoList;
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public int getPk(String branchName) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            branchRepository.setSession(session);
            int pk = branchRepository.getPk(branchName);
            return pk;
        }catch (Exception e){
            e.printStackTrace();
            return 70807080;
        }finally {
            session.close();
        }
    }

}
