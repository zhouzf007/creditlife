package com.entrobus.credit.wechat.service.impl;

import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.pojo.wechat.WechatUser;
import com.entrobus.credit.pojo.wechat.WechatUserExample;
import com.entrobus.credit.wechat.WechatConst;
import com.entrobus.credit.wechat.dao.WechatUserMapper;
import com.entrobus.credit.wechat.service.WechatUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WechatUserServiceImpl implements WechatUserService {
    @Autowired
    private WechatUserMapper wechatUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(WechatUserServiceImpl.class);

    public int countByExample(WechatUserExample example) {
        int count = this.wechatUserMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public WechatUser selectByPrimaryKey(String id) {
        return this.wechatUserMapper.selectByPrimaryKey(id);
    }

    public List<WechatUser> selectByExample(WechatUserExample example) {
        return this.wechatUserMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.wechatUserMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WechatUser record) {
        return this.wechatUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WechatUser record) {
        return this.wechatUserMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(WechatUserExample example) {
        return this.wechatUserMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(WechatUser record, WechatUserExample example) {
        return this.wechatUserMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(WechatUser record, WechatUserExample example) {
        return this.wechatUserMapper.updateByExample(record, example);
    }

    public int insert(WechatUser record) {
        return this.wechatUserMapper.insert(record);
    }

    public int insertSelective(WechatUser record) {
        return this.wechatUserMapper.insertSelective(record);
    }

    @Override
    public int saveWechatUser(WxMpUser wxMpUser) {
        WechatUser wechatUserInfo = new WechatUser();
        wechatUserInfo.setOpenId(wxMpUser.getOpenId());
        //没有经过微信开放平台的开发者认证资质是获取不到UnionId的
        //微信用户UnionId
        wechatUserInfo.setUnionId(wxMpUser.getUnionId());
        //微信用户的头像地址
        String headImgUrl = wxMpUser.getHeadImgUrl();
        wechatUserInfo.setHeadimgurl(headImgUrl);
        wechatUserInfo.setSubscribeTime(new Date());//公众号关注时间
        wechatUserInfo.setSex(wxMpUser.getSex());
        wechatUserInfo.setNickname(wxMpUser.getNickname()); //微信昵称
        wechatUserInfo.setCity(wxMpUser.getCity());
        wechatUserInfo.setProvince(wxMpUser.getProvince());
        wechatUserInfo.setCountry(wxMpUser.getCountry());
        wechatUserInfo.setLanguage(wxMpUser.getLanguage());
        wechatUserInfo.setSubscribeStatus(WechatConst.PublicAccount.SUBSCRIBE); //公众号关注状态

        WechatUserExample example = new WechatUserExample();
        example.createCriteria().andOpenIdEqualTo(wxMpUser.getOpenId());
        List<WechatUser> wechatUserInfoList = selectByExample(example);
        //微信用户已经存在
        if(CollectionUtils.isNotEmpty(wechatUserInfoList)){
            wechatUserInfo.setUpdateTime(new Date());
            //更新
            int result = updateByExampleSelective(wechatUserInfo,example);
            return result;
        }else {
            //不存在，新增
            wechatUserInfo.setHeadimgurl(wxMpUser.getHeadImgUrl());
            wechatUserInfo.setId(GUIDUtil.genRandomGUID());
            wechatUserInfo.setCreateTime(new Date());
            int result = insertSelective(wechatUserInfo);
            return result;
        }
    }

    @Override
    public void unSubscribe(String openId) {
        WechatUserExample example = new WechatUserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        WechatUser wechatUserInfo = new WechatUser();
        wechatUserInfo.setUnsubscribeTime(new Date());
        wechatUserInfo.setUpdateTime(new Date());
        wechatUserInfo.setSubscribeStatus(WechatConst.PublicAccount.UNSUBSCRIBE);
        updateByExampleSelective(wechatUserInfo,example);
        logger.info("更新WechatUserInfo的关注状态");
    }
}