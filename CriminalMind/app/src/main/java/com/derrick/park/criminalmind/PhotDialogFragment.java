package com.derrick.park.criminalmind;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.solver.Goal;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Created by aleksandrakorolczuk1 on 2017-07-13.
 */

public class PhotDialogFragment extends DialogFragment {


    private static final String ARG_PHOTO = "photo";
    public static final String EXTRA_PHOTO = "com.derrick.park.criminalmind.photo";
    private Crime mCrime;
    private Bitmap image;
    private Dialog mDialog;
    private ImageView mPhotoView;

public void PhotDialogFragment() {

}

    public PhotDialogFragment() {
    }


    public static PhotDialogFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, id);

        PhotDialogFragment fragment = new PhotDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        View v = LayoutInflater.from(getActivity()).inflate(R.layout.photo_dialogfragment, null);
        mDialog = new Dialog(getActivity());


        UUID id = (UUID) getArguments().getSerializable(ARG_PHOTO);
        mCrime = CrimeLab.get(getActivity()).getCrime(id);
        mDialog.setContentView(R.layout.photo_dialogfragment);
        File mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);
        Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
        mPhotoView.setImageBitmap(bitmap);
        mPhotoView =(ImageView) v.findViewById(R.id.crime_photo);
        return mDialog;


    }}

