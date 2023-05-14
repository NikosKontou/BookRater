package com.example.bookrater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AccessPhoneContacts extends AppCompatActivity {


    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ListView contactsListView;
    private ArrayList<String> contacts;
    private ArrayList<String> phoneNumbers;
    private String reviewMessage =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_phone_contacts);

        contactsListView = findViewById(R.id.contacts_list_view);

        requestContactsPermission();

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                reviewMessage = bundle.getString("message");
            }
        }
    }

    private void requestContactsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            loadContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            }
        }
    }

    private void loadContacts() {
        contacts = new ArrayList<>();
        phoneNumbers = new ArrayList<>();

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            while (cursor.moveToNext()) {
                if (nameColumnIndex != -1 && numberColumnIndex != -1) {
                    String name = cursor.getString(nameColumnIndex);
                    String phoneNumber = cursor.getString(numberColumnIndex);
                    contacts.add(name);
                    phoneNumbers.add(phoneNumber);
                }
            }

            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        contactsListView.setAdapter(adapter);

        contactsListView.setOnItemClickListener((parent, view, position, id) -> {
            String name = contacts.get(position);
            String phoneNumber = phoneNumbers.get(position);
            showCallDialog(name, phoneNumber);
        });
    }

    private void showCallDialog(String name, String phoneNumber) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message " + name)
                .setMessage("Do you want to message " + name + " at " + phoneNumber + "?")
                .setPositiveButton("Send review", (dialog, which) -> {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                    smsIntent.putExtra(Intent.EXTRA_TEXT, reviewMessage);
                    smsIntent.setData(Uri.parse("smsto:" + phoneNumber));
                    startActivity(smsIntent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}