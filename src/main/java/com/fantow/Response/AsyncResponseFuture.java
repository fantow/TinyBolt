package com.fantow.Response;

public class AsyncResponseFuture extends ResponseFuture {

    public AsyncResponseFuture(long requestId, long timeoutMillis) {
        super(requestId, timeoutMillis);
    }
}
