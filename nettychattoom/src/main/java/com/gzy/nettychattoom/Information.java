package com.gzy.nettychattoom;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import lombok.Data;

/**
 * 解析消息
 * 将后台发送过来的消息解析为Information
 * 后台发送消息道前台转换成json字符串
 */
@Data
public class Information {
    private static Gson gson = new Gson();
    //聊天室
    private String roomId;
    //用户id
    private String userId;
    // 用户名
    private String userName;
    // 所发送的消息
    private String message;

    public String getRoomId() {
        return roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void getMessage(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 将json报文转为 information
     * @param message 消息报文
     * @return
     * @throws Exception
     */
    public static Information strJson2Information(String message) throws Exception {
        return Strings.isNullOrEmpty(message) ? null : gson.fromJson(message, Information.class);
    }

    /**
     * 将information 转换为json
     * @return
     * @throws Exception
     */
    public String infoJson() throws Exception{
        return gson.toJson(this);
    }

    public Information setRoomId(String roomId){
        this.setRoomId(roomId);
        return this;
    }

}
