package com.jiangyi.zhaopin.service.serviceImpl;

import com.jiangyi.zhaopin.dao.SendMapper;
import com.jiangyi.zhaopin.model.Send;
import com.jiangyi.zhaopin.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendServiceImpl implements SendService{
    @Autowired
    SendMapper sendMapper;
    @Override
    public List<Send> selectAllByuId(Integer uId) {
        return sendMapper.selectAllByuId(uId);
    }

    @Override
    public List<Send> selectAllBycId(Integer cId) {
        return sendMapper.selectAllBycId(cId);
    }

    @Override
    public void insertsend(Send send) {
        sendMapper.insertsend(send);
    }

    @Override
    public void deleteBysId(Integer sId) {
        sendMapper.deleteBysId(sId);
    }

    @Override
    public void updatasend(Send send) {
        sendMapper.updatasend(send);
    }

    @Override
    public void deleteByiId(Integer iId) {
        sendMapper.deleteByiId(iId);
    }

    @Override
    public void insertsend2(Send send) {
        sendMapper.insertsend2(send);
    }

    @Override
    public int uniq(Send send) {
        return sendMapper.uniq(send);
    }
}
