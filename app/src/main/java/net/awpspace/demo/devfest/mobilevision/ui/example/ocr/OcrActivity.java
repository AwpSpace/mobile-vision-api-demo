package net.awpspace.demo.devfest.mobilevision.ui.example.ocr;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

import net.awpspace.demo.devfest.mobilevision.R;
import net.awpspace.demo.devfest.mobilevision.ui.example.MobileVisionBaseActivity;

/**
 * Created by Luceefer on 11/25/16.
 * AwpSpace
 * dev.awpspace@gmail.com
 */

public class OcrActivity extends MobileVisionBaseActivity {

    private static final String TAG = "OcrActivity";

    @Override
    protected void createCameraSource() {
        Context context = getApplicationContext();

        // A text recognizer is created to find text.  An associated processor instance
        // is set to receive the text recognition results and display graphics for each text block
        // on screen.
        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();
        textRecognizer.setProcessor(new OcrDetectorProcessor(mGraphicOverlay));

        if (!textRecognizer.isOperational()) {
            // Note: The first time that an app using a Vision API is installed on a
            // device, GMS will download a native libraries to the device in order to do detection.
            // Usually this completes before the app is run for the first time.  But if that
            // download has not yet completed, then the above call will not detect any text,
            // barcodes, or faces.
            //
            // isOperational() can be used to check if the required native libraries are currently
            // available.  The detectors will automatically become operational once the library
            // downloads complete on device.
            Log.w(TAG, "Detector dependencies are not yet available.");

            // Check for low storage.  If there is low storage, the native library will not be
            // downloaded, so detection will not become operational.
            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
            boolean hasLowStorage = registerReceiver(null, lowstorageFilter) != null;

            if (hasLowStorage) {
                Toast.makeText(this, R.string.low_storage_error, Toast.LENGTH_LONG).show();
                Log.w(TAG, getString(R.string.low_storage_error));
            }
        }

        // Creates and starts the camera.  Note that this uses a higher resolution in comparison
        // to other detection examples to enable the text recognizer to detect small pieces of text.
        mCameraSource =
                new CameraSource.Builder(getApplicationContext(), textRecognizer)
                        .setFacing(CameraSource.CAMERA_FACING_BACK)
                        .setRequestedPreviewSize(1280, 1024)
                        .setRequestedFps(2.0f)
                        .setAutoFocusEnabled(true)
                        .build();
    }
}
