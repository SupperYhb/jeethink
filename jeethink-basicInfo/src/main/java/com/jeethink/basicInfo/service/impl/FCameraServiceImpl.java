package com.jeethink.basicInfo.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.List;

import com.jeethink.requestutil.function.httprequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.basicInfo.mapper.FCameraMapper;
import com.jeethink.basicInfo.domain.FCamera;
import com.jeethink.basicInfo.service.IFCameraService;
import com.jeethink.common.core.text.Convert;

/**
 * 摄像头Service业务层处理
 * 
 * @author yhb
 * @date 2020-08-11
 */
@Service
public class FCameraServiceImpl implements IFCameraService 
{
    @Autowired
    private FCameraMapper fCameraMapper;

    /**
     * 查询摄像头
     * 
     * @param fCameraid 摄像头ID
     * @return 摄像头
     */
    @Override
    public FCamera selectFCameraById(String fCameraid)
    {
        return fCameraMapper.selectFCameraById(fCameraid);
    }

    /**
     * 查询摄像头列表
     * 
     * @param fCamera 摄像头
     * @return 摄像头
     */
    @Override
    public List<FCamera> selectFCameraList(FCamera fCamera)
    {
        return fCameraMapper.selectFCameraList(fCamera);
    }

    /**
     * 新增摄像头
     * 
     * @param fCamera 摄像头
     * @return 结果
     */
    @Override
    public int insertFCamera(FCamera fCamera)
    {
        return fCameraMapper.insertFCamera(fCamera);
    }

    /**
     * 修改摄像头
     * 
     * @param fCamera 摄像头
     * @return 结果
     */
    @Override
    public int updateFCamera(FCamera fCamera)
    {
        return fCameraMapper.updateFCamera(fCamera);
    }

    /**
     * 删除摄像头对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFCameraByIds(String ids)
    {
        return fCameraMapper.deleteFCameraByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除摄像头信息
     * 
     * @param fCameraid 摄像头ID
     * @return 结果
     */
    @Override
    public int deleteFCameraById(String fCameraid)
    {
        return fCameraMapper.deleteFCameraById(fCameraid);
    }

    /**
     * 验证人脸
     * */
    @Override
    public String verificationFace(String faceImg, String uuId) {
        String apiToken= httprequest.login();
        faceImg = new String(Base64.getDecoder().decode(faceImg));
        try {
            faceImg = URLDecoder.decode(faceImg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result=httprequest.verificationFace(uuId,faceImg,apiToken);
        if(result.indexOf("成功")!=-1) {
            return "";
        }else {
            return result;
        }
    }
}
