package com.nulpointerexception.server;

import com.nulpointerexception.server.proto.SignUpServiceGrpc;
import com.nulpointerexception.server.proto.BookingNexusServiceDemo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;


public class SignUpServiceImpl extends SignUpServiceGrpc.SignUpServiceImplBase {
    @Override
    public void signUp(com.nulpointerexception.server.proto.BookingNexusServiceDemo.UserData request,
                       io.grpc.stub.StreamObserver<com.nulpointerexception.server.proto.BookingNexusServiceDemo.SignUpResponse> responseObserver) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            System.out.println("================= At Reward service start============"+dtf.format(now));
            //Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BookingNexusServiceDemo.SignUpResponse signUpResponse = BookingNexusServiceDemo.SignUpResponse.newBuilder()
                .setResult(request.getUsername()+" has been upgraded to Gold category").build();
        responseObserver.onNext(signUpResponse);
        responseObserver.onCompleted();
        now = LocalDateTime.now();
        System.out.println("================= At Reward service stop============"+dtf.format(now));
    }

    @Override
    public void searchBookings(com.nulpointerexception.server.proto.BookingNexusServiceDemo.BookingSearch request,
                               io.grpc.stub.StreamObserver<com.nulpointerexception.server.proto.BookingNexusServiceDemo.BookingResponse> responseObserver) {
        BookingNexusServiceDemo.Booking booking = BookingNexusServiceDemo.Booking.newBuilder()
                .setBookingId(1)
                .setActorId(1)
                .setCreatedById(1)
                .setCropId(1)
                .setMachineId(1)
                .setActorType("OPERATOR").build();

        BookingNexusServiceDemo.Booking bookingTwo = BookingNexusServiceDemo.Booking.newBuilder()
                .setBookingId(2)
                .setActorId(1)
                .setCreatedById(1)
                .setCropId(2)
                .setMachineId(2)
                .setActorType("OPERATOR").build();

        BookingNexusServiceDemo.BookingResponse  bookingResponse = BookingNexusServiceDemo.BookingResponse.newBuilder()
                .addBookings(booking)
                .addBookings(bookingTwo)
                .build();

        responseObserver.onNext(bookingResponse);
        responseObserver.onCompleted();

    }


    public void findMachine(com.nulpointerexception.server.proto.BookingNexusServiceDemo.MachineRequest request,
                            io.grpc.stub.StreamObserver<com.nulpointerexception.server.proto.BookingNexusServiceDemo.Machine> responseObserver) {

        BookingNexusServiceDemo.Machine machine = BookingNexusServiceDemo.Machine.newBuilder()
                .setMachineId("1")
                .setLocation("Higher Ground Village")
                .build();

        BookingNexusServiceDemo.Machine machineTwo = BookingNexusServiceDemo.Machine.newBuilder()
                .setMachineId("2")
                .setLocation("Lower Ground Village")
                .build();

        Map<String, BookingNexusServiceDemo.Machine> machineMap = new HashMap<>();
        machineMap.put("1", machine);
        machineMap.put("2", machineTwo);

        responseObserver.onNext(machineMap.get(request.getMachineId()));
        responseObserver.onCompleted();

    }

    public void findCrop(com.nulpointerexception.server.proto.BookingNexusServiceDemo.CropRequest request,
                         io.grpc.stub.StreamObserver<com.nulpointerexception.server.proto.BookingNexusServiceDemo.Crop> responseObserver) {
        BookingNexusServiceDemo.Media mediaOne = BookingNexusServiceDemo.Media.newBuilder().setUrl("s3.amazon.com/1").build();
        BookingNexusServiceDemo.Media mediaTwo = BookingNexusServiceDemo.Media.newBuilder().setUrl("s3.amazon.com/2").build();
        BookingNexusServiceDemo.Crop crop = BookingNexusServiceDemo.Crop.newBuilder()
                .setCropId(1)
                .setName("Cotton")
                .setImageName("pic1.jpeg")
                .setMedia(mediaOne)
                .build();

        BookingNexusServiceDemo.Crop cropTwo = BookingNexusServiceDemo.Crop.newBuilder()
                .setCropId(2)
                .setImageName("pic2.jpeg")
                .setName("Ground Nut")
                .setMedia(mediaTwo)
                .build();

        Map<Integer, BookingNexusServiceDemo.Crop> cropMap = new HashMap<>();
        cropMap.put(1, crop);
        cropMap.put(2, cropTwo);

        System.out.println("Hello! In crops");

        responseObserver.onNext(cropMap.get(Integer.parseInt(request.getCropId())));
        responseObserver.onCompleted();

    }


}
