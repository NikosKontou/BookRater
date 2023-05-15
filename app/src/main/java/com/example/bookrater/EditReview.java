package com.example.bookrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;

public class EditReview extends AppCompatActivity {
    String title = null;

    int reviewId = 0;
    String author = null;
    String review = null;
    float rating = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);
        TextView authorEdit = findViewById(R.id.authorEdit);
        TextView titleEdit = findViewById(R.id.titleEdit);
        RatingBar ratingEdit = findViewById(R.id.editRating);
        TextInputEditText reviewEdit = findViewById(R.id.editReview);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                title = bundle.getString("title");
                author = bundle.getString("author");
                review = bundle.getString("review");
                rating = bundle.getFloat("rating");
                reviewId = bundle.getInt("id");

                authorEdit.setText(author);
                titleEdit.setText(title);
                ratingEdit.setRating(rating);
                reviewEdit.setText(review);

            }
        }


    }

    public void saveChanges(View view) {

        RatingBar ratingEdit = findViewById(R.id.editRating);
        TextInputEditText reviewEdit = findViewById(R.id.editReview);

        Review reviewObj = new Review(reviewId, title, author, reviewEdit.getText().toString(), ratingEdit.getRating());
        UpdateReviewLoader loader = new UpdateReviewLoader(this, reviewObj);
        loader.forceLoad();
        Intent intent = new Intent(EditReview.this, ViewReviews.class);
        Toast.makeText(EditReview.this, "Changes saved", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void deleteReview(View view) {
        DeleteReviewLoader loader = new DeleteReviewLoader(this, reviewId);
        loader.forceLoad();
        Intent intent = new Intent(EditReview.this, ViewReviews.class);
        Toast.makeText(EditReview.this, "Successfully deleted", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void qrCodeShare(View view) {

        // Get a reference to the ImageView
        ImageView qrCodeImageView = findViewById(R.id.qr_code_image_view);

// Generate the QR code bitmap from the string data
        Bitmap qrCodeBitmap = generateQRCode(createReviewText());

// Set the QR code bitmap to the ImageView
        if (qrCodeBitmap != null) {
            Button hideQr = findViewById(R.id.hideQr);
            hideQr.setVisibility(View.VISIBLE);
            qrCodeImageView.setImageBitmap(qrCodeBitmap);
            qrCodeImageView.setVisibility(View.VISIBLE);
        }

    }
    public void hideQr(View view){

        Button hideQr = findViewById(R.id.hideQr);
        hideQr.setVisibility(View.INVISIBLE);

        ImageView qrCodeImageView = findViewById(R.id.qr_code_image_view);
        qrCodeImageView.setVisibility(View.INVISIBLE);
    }

    public void smsShare(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("message", createReviewText());
        Intent intent = new Intent(EditReview.this, AccessPhoneContacts.class);
        intent.putExtras(bundle);
        startActivity(intent);


        //vanila contacts
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.putExtra(Intent.EXTRA_TEXT, createReviewText());
//        intent.setData(Uri.parse("smsto:"));
        // Start the activity to send the SMS
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }

    private String createReviewText(){
        return "Hi! i share with you my review for the book "+title+" of the author "+author+" \n"+review+"\nMy rating is "+String.valueOf(rating)+"/5";
    }

    private Bitmap generateQRCode(String data) {
        // Set the QR code encoding parameters
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        // Generate the QR code bitmap
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, 512, 512, hints);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }


}