package com.example.bookrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewReview extends AppCompatActivity {
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;
    private Button newReviewButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);

        mBookInput = (EditText)findViewById(R.id.bookInput);
        mTitleText = (TextView)findViewById(R.id.titleText);
        mAuthorText = (TextView)findViewById(R.id.authorText);
        newReviewButton = (Button)findViewById(R.id.button2);
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            new FetchBook(mTitleText, mAuthorText).execute(queryString);
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        } else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            } else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }


    public void newReview(View view) {
        String title = mTitleText.getText().toString();
        String author = mAuthorText.getText().toString();

        Intent intent = new Intent(NewReview.this, CreateNewReview.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("author", author);
        intent.putExtras(bundle);
        if(checkBookData()) {
            startActivity(intent);
        }else{
            Toast.makeText(NewReview.this, "First search a book", Toast.LENGTH_LONG).show();
        }
    }
    private boolean checkBookData(){
        if(this.mTitleText.getText().equals(getString(R.string.no_search_term)) || this.mTitleText.getText().equals(getString(R.string.no_network))|| this.mTitleText.getText().equals(getString(R.string.loading)) || this.mTitleText.getText().equals(getString(R.string.no_results)) || this.mTitleText.getText().equals("")){
            return false;
        }else{
            return true;
        }
    }
}