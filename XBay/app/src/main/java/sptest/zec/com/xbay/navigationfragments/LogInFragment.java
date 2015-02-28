package sptest.zec.com.xbay.navigationfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sptest.zec.com.xbay.Activities.HomePage;
import sptest.zec.com.xbay.Interfaces.LogInAPI;
import sptest.zec.com.xbay.R;
import sptest.zec.com.xbay.Utils.SharedPrefUser;

public class LogInFragment extends Fragment {

    @InjectView(R.id.editText_Email_Login)
    EditText editTxtEmailLogIn;
    @InjectView(R.id.editText_Password_Login)
    EditText editTxtPass;
    @InjectView(R.id.button_sign_in)
    Button btnSignIn;

    private static final String ENDPOINT = "http://webmedia.mk/api-x-bay";
    private String UserName;
    private String Password;
    private SharedPrefUser sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.inject(this, mView);

        sp = new SharedPrefUser(getActivity());

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //send username and password to the request
                UserName = editTxtEmailLogIn.getText().toString();
                Password = editTxtPass.getText().toString();
                System.out.println(editTxtEmailLogIn.getText().toString());
                System.out.println(editTxtPass.getText().toString());

                requestData("http://webmedia.mk/api-x-bay/api/Account/Validate?username={username}&password={password}");

            }
        });

        return mView;
    }

    private void requestData(String uri) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        LogInAPI api = adapter.create(LogInAPI.class);

        api.getFeed(UserName, Password, new Callback<Boolean>() {
            @Override
            public void success(Boolean aBoolean, Response response) {

                //if is success, log in, save user info and redirect to home page
                if (aBoolean == true) {

                    sp.createLoginSession(UserName, Password);
                    System.out.println(sp.getUserDetails());

                    Intent i = new Intent(getActivity(), HomePage.class);
                    startActivity(i);
                    Toast.makeText(getActivity(), "Welcome" + " " + UserName, Toast.LENGTH_SHORT).show();
                }

                System.out.println("SUCCESS");

            }

            @Override
            public void failure(RetrofitError error) {

                System.out.println("FAIL");

            }
        });
    }
}