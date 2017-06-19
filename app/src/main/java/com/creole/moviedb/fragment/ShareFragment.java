package com.creole.moviedb.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.creole.moviedb.R;
import com.creole.moviedb.model.Movie;

/**
 * Created by admin1234 on 6/7/17.
 */

public class ShareFragment extends Fragment {

    private Movie mMovie;
    private final static String KEY_MOVIE = "key_movie";
    TextView mTitle;
    TextView mId;
    Button mBtnCopyToClipBoard;
    Button mShare;


    public static ShareFragment getInstance(Movie paramMovie) {
        ShareFragment movieShareFrg = new ShareFragment();
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(KEY_MOVIE, paramMovie);
        movieShareFrg.setArguments(mBundle);
        return movieShareFrg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            mMovie = (Movie) getArguments().getSerializable(KEY_MOVIE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_fragment, container, false);
        mTitle = (TextView) view.findViewById(R.id.share_title);
        mId = (TextView) view.findViewById(R.id.share_movie_id);
        mBtnCopyToClipBoard = (Button) view.findViewById(R.id.btn_cpy_to_clipboard);
        mShare = (Button) view.findViewById(R.id.btn_share);
        mBtnCopyToClipBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager mClipboardManager = (ClipboardManager) getActivity().getSystemService(getContext().CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("text", mId.getText());
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getActivity(), "Text Copied", Toast.LENGTH_SHORT).show();

            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id = mId.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Id);
                intent.setType("text/plain");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, Id));
            }
        });
        String Title = (String.format(getResources().getString(R.string.share_title), mMovie.getTitle()));
        String Id = getResources().getString(R.string.share_url) + mMovie.getId();
        mTitle.setText(Title);
        mId.setText(Id);
        return view;
    }


}
