package com.example.taskdemo;

import android.content.Context;
import android.widget.MediaController;

public class CustomMediaController extends MediaController {

    public CustomMediaController(Context context) {
        super(context);
    }

    @Override
    public void show(int timeout) {
        super.show(0); }
}

