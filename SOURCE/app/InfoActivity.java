package app.android.lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;

public class InfoActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPass, edtUsername;
    RadioGroup rdbGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        anhXa();
        getSupportActionBar().setTitle("Account Info");
        Intent intent = getIntent();
        edtName.setText(intent.getStringExtra("Username"));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsMenuSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.Save){

        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void anhXa(){
        edtName = findViewById(R.id.name);
        edtEmail = findViewById(R.id.email);
        edtUsername = findViewById(R.id.User);
        edtPass = findViewById(R.id.Password);
    }
}
