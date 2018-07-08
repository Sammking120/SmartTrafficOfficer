package com.example.sammking.smarttrafficofficer;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    Button register;
    EditText First_Name, Last_Name, Email,Phone_number,Id_number, Password ;
    String F_Name_Holder, L_Name_Holder, EmailHolder,Phone_number_Holder,Id_number_Holder, PasswordHolder;
    String finalResult ;
    String HttpURL = "https://smarttrafficofficer.000webhostapp.com/Registration.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Assign Id'S
        First_Name = (EditText)findViewById(R.id.fname);
        Last_Name = (EditText)findViewById(R.id.lname);
        Email = (EditText)findViewById(R.id.email);
        Id_number= (EditText)findViewById(R.id.id_number);
        Phone_number = (EditText)findViewById(R.id.phone_number);
        Password = (EditText)findViewById(R.id.password);

        register = (Button)findViewById(R.id.btn_register);
        //log_in = (Button)findViewById(R.id.Login);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(F_Name_Holder,L_Name_Holder, EmailHolder,Id_number_Holder, Phone_number_Holder,PasswordHolder);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(SignUp.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }


            }
        });


    }

    public void CheckEditTextIsEmptyOrNot(){

        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        Id_number_Holder = Id_number.getText().toString();
        Phone_number_Holder = Phone_number.getText().toString();
        PasswordHolder = Password.getText().toString();


        if(TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder) || TextUtils.isEmpty(EmailHolder) ||TextUtils.isEmpty(Phone_number_Holder) || TextUtils.isEmpty(Id_number_Holder) || TextUtils.isEmpty(PasswordHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String fname, final String lname, final String email,final String phone_number,final String id_number, final String password){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(SignUp.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(SignUp.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("fname",params[0]);

                hashMap.put("lname",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("id_number",params[3]);

                hashMap.put("phone_number",params[4]);

                hashMap.put("password",params[5]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(fname,lname,email,id_number,phone_number, password);
    }

}

