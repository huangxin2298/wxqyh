package cn.edu.zhku.jsj.huangxin.component.base.thread;

import org.apache.http.conn.HttpClientConnectionManager;

/**
 * 清除httpclient无效的连接
 */
public class ClearConnectionsHandler extends Thread{

    private final HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public ClearConnectionsHandler(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
