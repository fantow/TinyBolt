package com.fantow.Response;

import com.fantow.Remoting.RemotingCallBack;
import com.fantow.RemotingCommand.Strategy.TimeoutStrategy;

/**
 * 用来封装Request信息以及Callback函数
 */
public abstract class ResponseFuture {
    // 请求ID
    private final long requestId;

    // 指定Response的Timeout
    private final long timeoutMillis;

    // 接收到的响应内容
    private RemotingContent responseContent;

    // Request的发送状态
    private boolean sendStatus;

    // 封装的错误信息，这里参考了FutureTask，不在发送过程中直接抛出异常
    private Throwable cause;

    // 创建时间戳(请求发送时创建)
    private final long createTimeStamp;

    // 可选项，可以传入超时处理策略
    private TimeoutStrategy timeoutStrategy;

    // 可选项，传入回调函数
    private RemotingCallBack callBack;

//    // 直接在底层引入失败重发机制
//    private int resendTimeMaximum = 3;

    public ResponseFuture(long requestId, long timeoutMillis) {
        this.requestId = requestId;
        this.timeoutMillis = timeoutMillis;
        this.createTimeStamp = System.currentTimeMillis();
    }





    public long getRequestId() {
        return requestId;
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public RemotingContent getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(RemotingContent responseContent) {
        this.responseContent = responseContent;
    }

    public boolean getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(boolean sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public TimeoutStrategy getTimeoutStrategy() {
        return timeoutStrategy;
    }

    public void setTimeoutStrategy(TimeoutStrategy timeoutStrategy) {
        this.timeoutStrategy = timeoutStrategy;
    }

    public RemotingCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(RemotingCallBack callBack) {
        this.callBack = callBack;
    }

//    public int getResendTimeMaximum() {
//        return resendTimeMaximum;
//    }
//
//    public void setResendTimeMaximum(int resendTimeMaximum) {
//        this.resendTimeMaximum = resendTimeMaximum;
//    }
}
