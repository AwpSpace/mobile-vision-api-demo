package net.awpspace.demo.devfest.mobilevision.ui.example;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;

import net.awpspace.demo.devfest.mobilevision.R;
import net.awpspace.demo.devfest.mobilevision.ui.camera.CameraSourcePreview;
import net.awpspace.demo.devfest.mobilevision.ui.camera.GraphicOverlay;

import java.io.IOException;

/**
 * Created by Luceefer on 11/25/16.
 * AwpSpace
 * dev.awpspace@gmail.com
 */

public abstract class MobileVisionBaseActivity extends AppCompatActivity {

    // intent request code to handle updating play services if needed.
    protected static final int RC_HANDLE_GMS = 9001;

    protected CameraSource mCameraSource;
    protected CameraSourcePreview mPreview;
    protected GraphicOverlay mGraphicOverlay;

    /**
     * Creates and starts the camera.  Note that this uses a higher resolution in comparison
     * to other detection examples to enable the barcode detector to detect small barcodes
     * at long distances.
     * <p>
     * Suppressing InlinedApi since there is a check that the minimum version is met before using
     * the constant.
     */
    abstract protected void createCameraSource();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_vision_scanner);

        mPreview = (CameraSourcePreview) findViewById(R.id.camera_preview);
        mGraphicOverlay = (GraphicOverlay) findViewById(R.id.graphic_overlay);

        createCameraSource();
    }

    /**
     * Restarts the camera.
     */
    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mPreview != null) {
            mPreview.stop();
        }
    }

    /**
     * Releases the resources associated with the camera source, the associated detectors, and the
     * rest of the processing pipeline.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPreview != null) {
            mPreview.release();
        }
    }

    private void startCameraSource() {
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS);
            dlg.show();
        }

        if (mCameraSource != null) {
            try {
                mPreview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                Log.e("Error", "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        }
    }
}
