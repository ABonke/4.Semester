package com.example.andreas.mandatorycanteenapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

public class LoggedInActivity extends AppCompatActivity {

    Intent intent;
    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView emailTextView;
    ImageView customerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ReadTask task = new ReadTask();
        task.execute("http://anbo-canteen.azurewebsites.net/Service1.svc/customers");
    }

    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence charSequence) {
            final List<Customer> customers = new ArrayList<>();
            intent = getIntent();
            try {
                JSONArray array = new JSONArray(charSequence.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String email = obj.getString("Email");
                    String firstName = obj.getString("Firstname");
                    String lastName = obj.getString("Lastname");
                    int id = obj.getInt("Id");
                    String password = obj.getString("Password");
                    String pictureUrl = obj.getString("PictureUrl");
                    Customer customer = new Customer(email, firstName, lastName, id, password, pictureUrl);
                    customers.add(customer);
                }
            } catch (JSONException ex) {
                Log.e("Customers", ex.getMessage());
            }

            String emailToCompare = intent.getStringExtra("Email");
            for (Customer customer : customers) {

                if (customer.getEmail().equals(emailToCompare))
                {
                    firstNameTextView = (TextView) findViewById(R.id.Customer_FirstName_Text);
                    lastNameTextView = (TextView) findViewById(R.id.Customer_LastName_Text);
                    emailTextView = (TextView) findViewById(R.id.Customer_Email_Text);
                    customerImageView = (ImageView) findViewById(R.id.Customer_imageView);

                    Picasso.with(LoggedInActivity.this)
                            .load(customer.getPictureUrl())
                            .into(customerImageView);


                    firstNameTextView.setText(customer.getFirstName());
                    lastNameTextView.setText(customer.getLastName());
                    emailTextView.setText(customer.getEmail());

                }


            }


        }


    }
}


