package com.and1ss.user_service.api.grpc;

import com.and1ss.user_service.*;
import com.and1ss.user_service.service.UserService;
import com.and1ss.user_service.service.model.LoginInfo;
import com.and1ss.user_service.service.model.RegisterInfo;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class AuthenticationService extends GrpcAuthenticationServiceGrpc.GrpcAuthenticationServiceImplBase {
    private final UserService userService;

    @Autowired
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void identifyByToken(
            GrpcAccessTokenIncomingDTO request,
            StreamObserver<GrpcAccountInfoRetrievalDTO> responseObserver
    ) {
        final var user = userService.authorizeUserByAccessToken(request.getToken());

        final var grpcDto = GrpcAccountInfoRetrievalDTO.newBuilder()
                .setId(user.getId().toString())
                .setName(user.getName())
                .setSurname(user.getSurname())
                .build();

        responseObserver.onNext(grpcDto);
        responseObserver.onCompleted();
    }

    @Override
    public void login(
            GrpcLoginCredentialsDTO request,
            StreamObserver<GrpcAccessTokenOutgoingDTO> responseObserver
    ) {
        final var credentials = new LoginInfo(request.getLogin(), request.getPassword());
        final var accessToken = userService.loginUser(credentials);

        final var grpcDto = GrpcAccessTokenOutgoingDTO.newBuilder()
                .setToken(accessToken.toString())
                .setCreatedAt(accessToken.getCreatedAt().toString())
                .build();

        responseObserver.onNext(grpcDto);
        responseObserver.onCompleted();
    }

    @Override
    public void register(
            GrpcRegisterInfoDTO request,
            StreamObserver<GrpcAccountInfoRetrievalDTO> responseObserver
    ) {
        final var registerInfo = new RegisterInfo(
                request.getName(), request.getSurname(),
                request.getLogin(), request.getPassword()
        );
        final var accountInfo = userService.registerUser(registerInfo);

        final var grpcDto = GrpcAccountInfoRetrievalDTO.newBuilder()
                .setName(accountInfo.getName())
                .setSurname(accountInfo.getSurname())
                .setId(accountInfo.getId().toString())
                .build();

        responseObserver.onNext(grpcDto);
        responseObserver.onCompleted();
    }

    @Override
    public void identifyUserById(
            GrpcUserIdDTO request,
            StreamObserver<GrpcAccountInfoRetrievalDTO> responseObserver
    ) {
        final var userId = UUID.fromString(request.getUserId());
        final var accountInfo = userService.findUserById(userId);

        final var grpcDto = GrpcAccountInfoRetrievalDTO.newBuilder()
                .setName(accountInfo.getName())
                .setSurname(accountInfo.getSurname())
                .setId(accountInfo.getId().toString())
                .build();

        responseObserver.onNext(grpcDto);
        responseObserver.onCompleted();
    }

    @Override
    public void identifyUsersByIds(
            GrpcUsersIdsDTO request,
            StreamObserver<GrpcUsersDTO> responseObserver
    ) {
        final var usersIds = request
                .getUsersIdsList()
                .asByteStringList()
                .stream()
                .map(ByteString::toStringUtf8)
                .map(UUID::fromString)
                .collect(Collectors.toList());

        final var users = userService.findUsersByListOfIds(usersIds)
                .stream()
                .map(user ->
                        GrpcAccountInfoRetrievalDTO.newBuilder()
                                .setId(user.getId().toString())
                                .setName(user.getName())
                                .setSurname(user.getSurname())
                                .build()
                )
                .collect(Collectors.toList());

        final var grpcDto = GrpcUsersDTO.newBuilder()
                .addAllUsers(users)
                .build();
        responseObserver.onNext(grpcDto);
        responseObserver.onCompleted();
    }
}
