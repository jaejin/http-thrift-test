package thrift;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import javax.servlet.http.HttpServlet;

/**
 * Created with IntelliJ IDEA.
 * User: skplanet
 * Date: 13. 4. 26.
 * Time: 오후 3:42
 * To change this template use File | Settings | File Templates.
 */
public class THttpTransport extends HttpServlet {

    public static class Args extends AbstractServerArgs<Args> {
        public Args(TServerTransport transport) {
            super(transport);
        }
    }

    public static abstract class AbstractServerArgs<T extends AbstractServerArgs<T>> {
        final TServerTransport serverTransport;
        TProcessorFactory processorFactory;
        TTransportFactory inputTransportFactory = new TTransportFactory();
        TTransportFactory outputTransportFactory = new TTransportFactory();
        TProtocolFactory inputProtocolFactory = new TBinaryProtocol.Factory();
        TProtocolFactory outputProtocolFactory = new TBinaryProtocol.Factory();

        public AbstractServerArgs(TServerTransport transport) {
            serverTransport = transport;
        }

        public T processorFactory(TProcessorFactory factory) {
            this.processorFactory = factory;
            return (T) this;
        }

        public T processor(TProcessor processor) {
            this.processorFactory = new TProcessorFactory(processor);
            return (T) this;
        }

        public T transportFactory(TTransportFactory factory) {
            this.inputTransportFactory = factory;
            this.outputTransportFactory = factory;
            return (T) this;
        }

        public T inputTransportFactory(TTransportFactory factory) {
            this.inputTransportFactory = factory;
            return (T) this;
        }

        public T outputTransportFactory(TTransportFactory factory) {
            this.outputTransportFactory = factory;
            return (T) this;
        }

        public T protocolFactory(TProtocolFactory factory) {
            this.inputProtocolFactory = factory;
            this.outputProtocolFactory = factory;
            return (T) this;
        }

        public T inputProtocolFactory(TProtocolFactory factory) {
            this.inputProtocolFactory = factory;
            return (T) this;
        }

        public T outputProtocolFactory(TProtocolFactory factory) {
            this.outputProtocolFactory = factory;
            return (T) this;
        }
    }

    /**
     * Core processor
     */
    protected TProcessorFactory processorFactory_;

    /**
     * Server transport
     */
    protected TServerTransport serverTransport_;

    /**
     * Input Transport Factory
     */
    protected TTransportFactory inputTransportFactory_;

    /**
     * Output Transport Factory
     */
    protected TTransportFactory outputTransportFactory_;

    /**
     * Input Protocol Factory
     */
    protected TProtocolFactory inputProtocolFactory_;

    /**
     * Output Protocol Factory
     */
    protected TProtocolFactory outputProtocolFactory_;

    private boolean isServing;

    protected TServerEventHandler eventHandler_;

    protected THttpTransport(AbstractServerArgs args) {
        processorFactory_ = args.processorFactory;
        serverTransport_ = args.serverTransport;
        inputTransportFactory_ = args.inputTransportFactory;
        outputTransportFactory_ = args.outputTransportFactory;
        inputProtocolFactory_ = args.inputProtocolFactory;
        outputProtocolFactory_ = args.outputProtocolFactory;
    }

}
