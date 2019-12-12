package com.example.test1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView,mScannerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);

        setContentView(R.layout.activity_main);
        mScannerView=findViewById(R.id.mScannerView);
    }

    @Override
    public void handleResult(Result rawResult) {

        // Do something with the result here
        // Prints the scan format (qrcode, pdf417 etc.)
        String scantext=rawResult.getText();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(scantext);
        AlertDialog alert1 = builder.create();
        alert1.show();

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }


}
