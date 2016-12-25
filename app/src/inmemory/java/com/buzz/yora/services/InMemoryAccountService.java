package com.buzz.yora.services;

import com.buzz.yora.infrastructure.Auth;
import com.buzz.yora.infrastructure.User;
import com.buzz.yora.infrastructure.YoraApplication;
import com.squareup.otto.Subscribe;

public class InMemoryAccountService extends BaseInMemoryService {

    private static final String TAG = InMemoryAccountService.class.getSimpleName();

    public InMemoryAccountService(YoraApplication application) {
        super(application);
    }

    @Subscribe
    public void updateProfile(final Account.UpdateProfileRequest request) {
        final Account.UpdateProfileResponse response = new Account.UpdateProfileResponse();

        if (request.displayName.equals("Rodrigo")) {
            response.setPropertyError("displayName", "You may not be named Rodrigo");
        }

        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                User user = application.getAuth().getUser();
                user.setDisplayName(request.displayName);
                user.setEmail(request.email);

                bus.post(response);
                bus.post(new Account.UserDetailsUpdatedEvent(user));
            }
        }, 2000, 3000);

    }

    @Subscribe
    public void updateAvatar(final Account.ChangeAvatarRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                User user = application.getAuth().getUser();
                user.setAvatarUrl(request.newAvatarUri.toString());

                bus.post(new Account.ChangeAvatarResponse());
                bus.post(new Account.UserDetailsUpdatedEvent(user));
            }
        }, 4000, 5000);
    }

    @Subscribe
    public void changePassword(Account.ChangePasswordRequest request) {
        Account.ChangePasswordResponse response = new Account.ChangePasswordResponse();

        if (!request.newPassword.equals(request.confirmNewPaswword)) {
            response.setPropertyError("confirmNewPassword", "Passwords must match!");
        }

        if (request.newPassword.length() < 3) {
            response.setPropertyError("newPassword", "Password must be larger than 3 characters");
        }
        postDelayed(response);
    }

    @Subscribe
    public void loginWithUserName(final Account.LoginWithUserNameRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.LoginWithUserNameResponse response = new Account.LoginWithUserNameResponse();

                if (request.userName.equals("rodrigo")) {
                    response.setPropertyError("userName", "Invalid UserName or Password");
                }
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);
    }

    @Subscribe
    public void loginWithExternalToken(Account.LoginWithExternalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.LoginWithExternalTokenResponse response = new Account.LoginWithExternalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);
    }

    @Subscribe
    public void loginWithLocalToken(Account.LoginWithLocalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.LoginWithLocalTokenResponse response = new Account.LoginWithLocalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);
    }

    @Subscribe
    public void register(Account.RegisterRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.RegisterResponse response = new Account.RegisterResponse();
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);
    }

    @Subscribe
    public void registerWithExternalToken(Account.RegisterWithExternalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.RegisterWithExternalTokenResponse response = new Account.RegisterWithExternalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);
    }


    private void loginUser(Account.UserResponse response) {
        Auth auth = application.getAuth();
        User user = auth.getUser();

        user.setDisplayName("Rodrigo Rocha");
        user.setUserName("7odri9o");
        user.setEmail("rodrigo.srrocha@gmail.com");
        user.setAvatarUrl("http://www.gravatar.com/avatar/1?d=identicon");
        user.setLoggedIn(true);
        user.setId(123);
        bus.post(new Account.UserDetailsUpdatedEvent(user));

        auth.setAuthToken("fakeAuthToken");

        response.displayName = user.getDisplayName();
        response.userName = user.getUserName();
        response.email = user.getEmail();
        response.avatarUri = user.getAvatarUrl();
        response.id = user.getId();
        response.authToken = auth.getAuthToken();

    }
}
