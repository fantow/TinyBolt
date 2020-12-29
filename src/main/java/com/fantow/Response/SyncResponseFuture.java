package com.fantow.Response;

import com.fantow.Remoting.RemotingCallBack;
import com.fantow.RemotingCommand.Strategy.TimeoutStrategy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SyncResponseFuture extends ResponseFuture {

    private final CountDownLatch countDownLatch;

    public SyncResponseFuture(long requestId, long timeoutMillis) {
        super(requestId,timeoutMillis);
        // 在构造函数中初始化CountDownLatch
        this.countDownLatch = new CountDownLatch(1);
    }

    @Override
    public void setResponseContent(RemotingContent responseContent) {
        super.setResponseContent(responseContent);
        countDownLatch.countDown();
    }

    // 在同步模型中，发送会阻塞至接收到响应
    public RemotingContent waitResponse() throws InterruptedException {
        countDownLatch.await(this.getTimeoutMillis(), TimeUnit.MILLISECONDS);
        RemotingContent response = getResponseContent();
        if(response != null){
            return response;
        }
        return null;
    }
}
