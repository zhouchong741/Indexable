package cn.xiyuanzaixian.xxx.indexable.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xiyuanzaixian.xxx.indexable.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment {


    public static FourthFragment newInstance() {
        return new FourthFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

}
