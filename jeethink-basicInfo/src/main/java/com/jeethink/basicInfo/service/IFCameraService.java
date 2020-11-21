package com.jeethink.basicInfo.service;

import java.util.List;
import com.jeethink.basicInfo.domain.FCamera;

/**
 * 摄像头Service接口
 * 
 * @author yhb
 * @date 2020-08-11
 */
public interface IFCameraService 
{
    /**
     * 查询摄像头
     * 
     * @param fCameraid 摄像头ID
     * @return 摄像头
     */
    public FCamera selectFCameraById(String fCameraid);

    /**
     * 查询摄像头列表
     * 
     * @param fCamera 摄像头
     * @return 摄像头集合
     */
    public List<FCamera> selectFCameraList(FCamera fCamera);

    /**
     * 新增摄像头
     * 
     * @param fCamera 摄像头
     * @return 结果
     */
    public int insertFCamera(FCamera fCamera);

    /**
     * 修改摄像头
     * 
     * @param fCamera 摄像头
     * @return 结果
     */
    public int updateFCamera(FCamera fCamera);

    /**
     * 批量删除摄像头
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFCameraByIds(String ids);

    /**
     * 删除摄像头信息
     * 
     * @param fCameraid 摄像头ID
     * @return 结果
     */
    public int deleteFCameraById(String fCameraid);

    /**
     * 验证人脸
     * */
    public String verificationFace(String faceImg,String uuId);
}
