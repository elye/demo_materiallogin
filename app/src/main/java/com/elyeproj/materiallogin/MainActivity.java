package com.elyeproj.materiallogin;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText = null;
    private EditText passwordEditText = null;
    private TextInputLayout emailTextInputLayout = null;
    private CustomTextInputLayout passworTextInputLayout = null;
    private Button registerButton = null;
    private static final int MIN_PASSWORD_LENGTH = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        setupListener();
    }

    private void initializeView() {
        emailEditText = (EditText)findViewById(R.id.input_email);
        passwordEditText = (EditText)findViewById(R.id.input_password);
        emailTextInputLayout = (TextInputLayout)findViewById(R.id.input_layout_email);
        passworTextInputLayout = (CustomTextInputLayout)findViewById(R.id.input_layout_password);
        registerButton = (Button)findViewById(R.id.btn_register);
    }

    private void setupListener() {
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                validateEmail(false);
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                validatePassword(false);
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInput();
            }
        });
    }

    private void validateInput() {
        validateEmail(true);
        validatePassword(true);
    }

    private Boolean validatePassword(boolean showError) {
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(password) || password.length() < MIN_PASSWORD_LENGTH) {
            if (!passworTextInputLayout.isErrorEnabled()) {
                passworTextInputLayout.setHelperTextEnabled(true);
            }
            if (showError) {
                passworTextInputLayout.setError(getString(R.string.password_helper_text));
            }
            return false;
        }
        passworTextInputLayout.setErrorEnabled(false);
        passworTextInputLayout.setHelperTextEnabled(false);

        return true;
    }

    private Boolean validateEmail(boolean showError) {
        String email = emailEditText.getText().toString();

        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (showError) {
                emailTextInputLayout.setError(getString(R.string.err_msg_email));
            }
            return false;
        }

        emailTextInputLayout.setErrorEnabled(false);
        return true;
    }


}
