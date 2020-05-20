package com.nulpointerexception.server;

import com.nulpointerexception.server.proto.SignUpServiceGrpc;
import com.nulpointerexception.server.proto.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SignUpServiceImpl extends SignUpServiceGrpc.SignUpServiceImplBase {
    @Override
    public void signUp(com.nulpointerexception.server.proto.User.UserData request,
                       io.grpc.stub.StreamObserver<com.nulpointerexception.server.proto.User.SignUpResponse> responseObserver) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            System.out.println("================= At Reward service start============"+dtf.format(now));
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User.SignUpResponse signUpResponse = User.SignUpResponse.newBuilder()
                .setResult(request.getUsername()+" has been upgraded to Gold category").build();
        responseObserver.onNext(signUpResponse);
        responseObserver.onCompleted();
        now = LocalDateTime.now();
        System.out.println("================= At Reward service stop============"+dtf.format(now));
    }
}
