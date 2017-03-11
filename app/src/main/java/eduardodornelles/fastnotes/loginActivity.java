package eduardodornelles.fastnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;

public class loginActivity extends Activity {
    TextView txtStatus;
    LoginButton login_button;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        initializeControls();
        loginWithFB();


    }

private void initializeControls(){
    callbackManager = CallbackManager.Factory.create();
    txtStatus = (TextView)findViewById(R.id.txtStatus);
    login_button = (LoginButton)findViewById(R.id.login_button);
}

private void loginWithFB(){

    LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            txtStatus.setText("Login com sucesso\n" + loginResult.getAccessToken());
        }

        @Override
        public void onCancel() {
                txtStatus.setText("Login Cancelled");
        }

        @Override
        public void onError(FacebookException error) {
            txtStatus.setText("Login Error" + error.getMessage());
        }
    });

}


@Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
    super.onActivityResult(requestCode, resultCode, data);
    callbackManager.onActivityResult(requestCode, resultCode, data);
}


}




