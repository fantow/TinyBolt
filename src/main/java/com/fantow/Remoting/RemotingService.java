package com.fantow.Remoting;

import com.fantow.Response.RemotingContent;
import com.fantow.Response.ResponseFuture;
import io.netty.channel.Channel;

// 描述Remoting的基础功能
public interface RemotingService {

    RemotingContent syncInvoke(Channel channel, RemotingContent request,final long timeoutMillis);

    void asyncInvoke(Channel channel,RemotingContent request,final long timeoutMillis,final RemotingCallBack callBack);

    void onewayInvoke(Channel channel,RemotingContent request,final long timeoutMillis);
}
