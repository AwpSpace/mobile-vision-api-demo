package net.awpspace.demo.devfest.mobilevision.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.awpspace.demo.devfest.mobilevision.R;
import net.awpspace.demo.devfest.mobilevision.ui.example.barcode.BarcodeActivity;
import net.awpspace.demo.devfest.mobilevision.ui.example.face.GooglyEyesActivity;
import net.awpspace.demo.devfest.mobilevision.ui.example.multi.MultiTrackerActivity;
import net.awpspace.demo.devfest.mobilevision.ui.example.ocr.OcrActivity;
import net.awpspace.demo.devfest.mobilevision.ui.example.opencv.OpenCvFaceDetectionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luceefer on 11/25/16.
 * AwpSpace
 * dev.awpspace@gmail.com
 */

public class ExampleListFragment extends Fragment implements ExampleListAdapter.ExampleHandler {

    @BindView(R.id.example_list)
    RecyclerView recyclerView;

    public static Fragment newInstance() {
        return new ExampleListFragment();
    }

    @Override
    public void viewExample(int index) {
        if (((ExampleListActivity) getActivity()).checkForPermission()) {
            switch (index) {
                case 0:
                    startActivity(new Intent(getActivity(), GooglyEyesActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), BarcodeActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), OcrActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getActivity(), MultiTrackerActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(getActivity(), OpenCvFaceDetectionActivity.class));
                    break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ExampleListAdapter(this));
    }
}
