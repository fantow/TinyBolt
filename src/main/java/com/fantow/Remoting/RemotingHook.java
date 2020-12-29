package com.fantow.Remoting;

import com.fantow.Response.RemotingContent;

/**
 * 提供三种影响Remoting的方式
 *  1.发送前
 *  2.发送后
 *  3.接收到响应后
 */
public interface RemotingHook {

    void applyBeforeSendRequest(RemotingContent request);

    void applyAfterSendRequest(RemotingContent request);

    void applyAfterRecvResponse(RemotingContent request,RemotingContent response);
}
