package server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.*;
import user.UserItemService;
import user.UserItemServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: skplanet
 * Date: 13. 4. 26.
 * Time: 오후 2:25
 * To change this template use File | Settings | File Templates.
 */
public class UserServer {
    public UserItemServiceImpl userItemService;
    public UserItemService.Processor<UserItemServiceImpl> processor;

    public UserServer() {
        userItemService = new UserItemServiceImpl();
        processor = new UserItemService.Processor<UserItemServiceImpl>(userItemService);
    }


    public void start() {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    TServerTransport transport = null;
                    try {
                        transport = new TServerSocket(9090);
                    } catch (TTransportException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    TServer tServer = new TSimpleServer(new TServer.Args(transport).processor(processor));

                    tServer.serve();
                }
            });
            thread.start();
    }

    public static void main(String[] args) {
        UserServer userServer = new UserServer();
        userServer.start();
    }
}
