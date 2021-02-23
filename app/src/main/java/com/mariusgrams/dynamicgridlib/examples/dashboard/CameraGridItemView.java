package com.mariusgrams.dynamicgridlib.examples.dashboard;

import android.content.Context;
import android.widget.ImageView;
import android.widget.VideoView;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegSurfaceView;
import com.mariusgrams.dynamicgrid.GridItemView;
import com.mariusgrams.dynamicgridlib.R;

public class CameraGridItemView extends GridItemView {

    private MjpegSurfaceView ivCamera;

    public CameraGridItemView(Context context) {
        super(context, R.layout.camera_gridview);

        ivCamera = findViewById(R.id.ivCamera);

        Mjpeg.newInstance()
                .credential("user", "allesfrisch.99")
                .open("http://62.68.193.82:8002/mjpg/video.mjpg", 5)
                .subscribe(inputStream -> {
                    ivCamera.setSource(inputStream);
                    ivCamera.setDisplayMode(DisplayMode.BEST_FIT);
                    ivCamera.showFps(true);
                });
    }
}
