package app.android.da_android_tour_manager.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.android.da_android_tour_manager.LoginActivity;
import app.android.da_android_tour_manager.R;

public class RegisterActivity extends AppCompatActivity {

    Button btnComebackLogin, btnSignup, btnConnectFacebook;
    EditText edtEmail, edtPass, edtConfirmPass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // ánh xạ
        mapping();

        // sự kiện click vào button đã có tài khoản
        btnComebackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // sự kiện click button đăng ký tài khoản
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                String confirmPass = edtConfirmPass.getText().toString().trim();
                if(email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Thông tin bạn nhập chưa đầy đủ !!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(checkEmail(email) == 0)
                {
                    Toast.makeText(RegisterActivity.this, "Email của bạn chưa hợp lệ !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.compareTo(confirmPass) != 0)
                {
                    Toast.makeText(RegisterActivity.this,"Mật khẩu chưa trùng khớp, vui lòng nhập lại !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.length() < 6)
                {
                    Toast.makeText(RegisterActivity.this,"Mật khẩu phải lớn hơn 5 kí tự vui lòng nhập lại !!",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this,"Đăng ký thất bại !!!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("email",edtEmail.getText().toString().trim());
                            intent.putExtra("password",edtPass.getText().toString().trim());
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });


            }
        });

        // sự kiện click button kết nối bằng facebook
        btnConnectFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this,"COMING SOON ^_^",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ánh xạ
    public void mapping(){
        btnComebackLogin = findViewById(R.id.btnComebackLogin);
        btnSignup = findViewById(R.id.btnSignUp);
        btnConnectFacebook = findViewById(R.id.btnSignUpFacebook);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);
        edtConfirmPass = findViewById(R.id.edtConfirmPassword);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // kiểm tra email
    public static int checkEmail(String email){
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if(matcher.find())
        {
            return 1; // hợp lệ
        }
        else
        {
            return 0; // không hợp lệ
        }
    }
}
