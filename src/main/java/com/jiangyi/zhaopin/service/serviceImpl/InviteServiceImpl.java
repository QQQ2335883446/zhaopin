package com.jiangyi.zhaopin.service.serviceImpl;

import com.jiangyi.zhaopin.dao.InviteMapper;
import com.jiangyi.zhaopin.model.Invite;
import com.jiangyi.zhaopin.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘信息服务层
 */
@Service
public class InviteServiceImpl implements InviteService {
    @Autowired
    InviteMapper inviteMaper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Invite> selectAll() {
        return inviteMaper.selectAll();
    }

    /**
     * 通过cid查
     * @param cId
     * @return
     */
    @Override
    public List<Invite> selectAllByCID(Integer cId) {
        return inviteMaper.selectAllByCID(cId);
    }

    /**
     * 删除
     * @param iId
     */
    @Override
    public void deleteById(Integer iId) {
        inviteMaper.deleteById(iId);
    }

    /**
     * 插入
     * @param invite
     */
    @Override
    public void insert(Invite invite) {
        inviteMaper.insert(invite);
    }

    /**
     * 更新
     * @param invite
     */
    @Override
    public void updata(Invite invite) {
        inviteMaper.updata(invite);
    }

    /**
     * 通过iid查
     * @param iId
     * @return
     */
    @Override
    public Invite selectByIId(Integer iId) {
        return inviteMaper.selectByIId(iId);
    }

    /**
     *
     *模糊搜索
     * @param iName
     * @return
     */
    @Override
    public List<Invite> selectlike(String iName) {
        return inviteMaper.selectlike(iName);
    }
}
