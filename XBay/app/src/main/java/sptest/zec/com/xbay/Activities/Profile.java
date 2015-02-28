package sptest.zec.com.xbay.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sptest.zec.com.xbay.Interfaces.ProfileAPI;
import sptest.zec.com.xbay.Models.ProfileModel;
import sptest.zec.com.xbay.R;
import sptest.zec.com.xbay.Utils.SharedPrefUser;

public class Profile extends Activity {

    private static final String ENDPOINT = "http://webmedia.mk/api-x-bay";
    @InjectView(R.id.btn_log_out)
    Button btnLogOut;
    @InjectView(R.id.username)
    TextView txt_username;
    @InjectView(R.id.address)
    TextView address;
    @InjectView(R.id.city)
    TextView city;
    @InjectView(R.id.country)
    TextView country;
    @InjectView(R.id.email)
    TextView email;
    private SharedPrefUser sp;
    private String username;
    private String password;
    private ProfileModel profileModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        ButterKnife.inject(this);

        sp = new SharedPrefUser(this);

        username = sp.getUserName();
        password = sp.getPass();

        System.out.println(password);
        System.out.println(username);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log out user, using shared preference method
                sp.logoutUser();
                Toast.makeText(getApplicationContext(), "Bye Bye", Toast.LENGTH_SHORT).show();
                System.out.println(sp.getUserDetails());
            }
        });

        requestData("http://webmedia.mk/api-x-bay/api/Profile/GetUserProfile?username={username}&password={password}");
    }

    private void requestData(String uri) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        ProfileAPI api = adapter.create(ProfileAPI.class);

        api.getFeed(username, password, new Callback<ProfileModel>() {

            @Override
            public void success(ProfileModel profileModels, Response response) {

                profileModel = profileModels;

                txt_username.setText(profileModel.getFirstName());
                address.setText(profileModel.getAddress());
                city.setText(profileModel.getCity());
                country.setText(profileModel.getCountry());
                email.setText(profileModel.getEmail());

                System.out.println("SUCCESS");

            }

            @Override
            public void failure(RetrofitError error) {

                System.out.println("FAIL");

            }
        });
    }
}
