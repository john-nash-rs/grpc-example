package com.nulpointerexception.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    private Server server;

    private void start() throws IOException {
        /* The port on which the com.nulpointerexception.server should run */
        int port = 8089;
        server = ServerBuilder.forPort(port).addService(new SignUpServiceImpl()).build().start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread() {
                            @Override
                            public void run() {
                                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                                Application.this.stop();
                                System.err.println("*** server shut down");
                            }
                        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Application server = new Application();
        server.start();
        server.blockUntilShutdown();
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
