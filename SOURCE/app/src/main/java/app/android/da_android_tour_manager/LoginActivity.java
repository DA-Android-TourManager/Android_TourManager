package app.android.da_android_tour_manager;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.android.da_android_tour_manager.common.UserCommon;
import app.android.da_android_tour_manager.fragment.RegisterActivity;
import app.android.da_android_tour_manager.model.KhachHang;

public class LoginActivity extends AppCompatActivity {

    Button btnSignIn, btnSkip, btnSingup, btnConnectFacebook;
    EditText edtUser, edtPass;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acitivity);

        // ánh xạ
        mapping();

        // xử lý cho button đăng nhập
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final String user = edtUser.getText().toString().trim();
                    String pass = edtPass.getText().toString().trim();

                    if(RegisterActivity.checkEmail(user) == 0)
                    {
                        Toast.makeText(LoginActivity.this, "Email của bạn chưa hợp lệ !!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    firebaseAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                UserCommon.email = user;
                                UserCommon.checkDangNhap = 1;

                                DatabaseReference khRef = FirebaseDatabase.getInstance().getReference("KhachHang");
                                khRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for(DataSnapshot item : dataSnapshot.getChildren())
                                        {
                                            KhachHang kh = item.getValue(KhachHang.class);
                                            if(kh.getEmail().equals(user))
                                            {
                                                UserCommon.maKhachHang = item.getKey();
                                                return;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                catch(Exception exception){
                    Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu chưa chính xác",Toast.LENGTH_SHORT).show();
                }
            }
        });


        // xử lý sự kiện bỏ qua đăng nhập
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserCommon.checkDangNhap = 0;
                UserCommon.email = null;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // xử lý sự kiện đăng nhập bằng facebook
        btnConnectFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"COMING SOON ^_^", Toast.LENGTH_SHORT).show();
            }
        });

        // xử lý sự kiện đăng ký
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void mapping(){
        btnSignIn = findViewById(R.id.btnSignIn);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPassword);
        btnSkip = findViewById(R.id.btnSkip);
        firebaseAuth = FirebaseAuth.getInstance();
        btnConnectFacebook =findViewById(R.id.btnSignUpFacebook);
        btnSingup = findViewById(R.id.btnSignUp);
    }
}
