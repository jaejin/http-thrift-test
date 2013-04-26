package server;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import user.UserItemService;
import user.UserItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: skplanet
 * Date: 13. 4. 26.
 * Time: 오후 3:39
 * To change this template use File | Settings | File Templates.
 */
public class UserServerServlet extends HttpServlet {


    TTransportFactory inputTransportFactory = new TTransportFactory();
    TTransportFactory outputTransportFactory = new TTransportFactory();
    TProtocolFactory inputProtocolFactory = new TBinaryProtocol.Factory();
    TProtocolFactory outputProtocolFactory = new TBinaryProtocol.Factory();

    public UserItemServiceImpl userItemService;
    public UserItemService.Processor<UserItemServiceImpl> processor;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TIOStreamTransport client= new TIOStreamTransport(request.getInputStream(),response.getOutputStream());

        userItemService = new UserItemServiceImpl();
        processor = new UserItemService.Processor<UserItemServiceImpl>(userItemService);

        TTransport inputTransport = inputTransportFactory.getTransport(client);
        TTransport outputTransport = outputTransportFactory.getTransport(client);
        TProtocol inputProtocol = inputProtocolFactory.getProtocol(inputTransport);
        TProtocol outputProtocol = outputProtocolFactory.getProtocol(outputTransport);

        try {
            processor.process(inputProtocol, outputProtocol);
        } catch (TException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
