package client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import user.UserItem;
import user.UserItemList;
import user.UserItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: skplanet
 * Date: 13. 4. 26.
 * Time: 오후 4:19
 * To change this template use File | Settings | File Templates.
 */
public class HttpUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        try {
            THttpClient tHttpClient = new THttpClient("http://localhost:8081/userItemServer");

            TProtocol tProtocol = new TBinaryProtocol(tHttpClient);

            UserItemService.Client client = new UserItemService.Client(tProtocol);
            UserItemList list = client.getUserItemList("");


            PrintWriter writer = response.getWriter();
            for(UserItem userItem : list.getItemList())  {
                writer.printf(" user id = %s , user item =  %s",userItem.getId(),userItem.getItem());
            }

            writer.close();


        } catch (TTransportException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
