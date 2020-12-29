package com.fantow.Remoting;

import com.fantow.Response.RemotingContent;
import com.fantow.Response.ResponseFuture;
import com.fantow.Response.SyncResponseFuture;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRemoting extends AbstractLifeCycle implements RemotingService{

    private static Logger logger = LoggerFactory.getLogger(AbstractRemoting.class);

    private Map<Integer,ResponseFuture> responseFutureMap = new ConcurrentHashMap<>(128);



    @Override
    public RemotingContent syncInvoke(Channel channel, RemotingContent request, long timeoutMillis) {

        int requestId = request.getRequestId();

        // 封装responseFuture
        SyncResponseFuture responseFuture = new SyncResponseFuture(requestId,timeoutMillis);

        responseFutureMap.put(requestId,responseFuture);

        SocketAddress remoteAddress = channel.remoteAddress();
        try {
            // 立即发送
            channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
                // 消息写入socket的发送缓冲区后就执行
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        responseFuture.setSendStatus(true);
                        return;
                    } else {
                        responseFuture.setSendStatus(false);
                        responseFuture.setCause(channelFuture.cause());

                        responseFutureMap.remove(requestId);
                        logger.warn("The message sends to channel <" + remoteAddress + "> failed.", channelFuture.cause());
                    }
                }
            });

            // 这里会阻塞至该Request收到Response
            try {
                if (responseFuture.getSendStatus() == true) {
                    RemotingContent response = responseFuture.waitResponse();
                    if (response != null) {
                        return response;
                    }
                }
            } catch (InterruptedException e) {
                // 可能因为等待时间过长，而造成超时
                e.printStackTrace();
                responseFuture.setCause(e);
                logger.warn("The messageId: " + responseFuture.getRequestId() + " can't get it's response in timeoutMillis.", e);
            }
        }finally {
            if(responseFutureMap.containsKey(requestId)){
                responseFutureMap.remove(requestId);
            }
        }

        return null;
    }

    @Override
    public void asyncInvoke(Channel channel, RemotingContent request, long timeoutMillis, RemotingCallBack callBack) {

    }

    @Override
    public void onewayInvoke(Channel channel, RemotingContent request, long timeoutMillis) {

    }
}
