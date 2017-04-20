package cn.xiyuanzaixian.xxx.indexable.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.blankj.aloglibrary.ALog;

import cn.xiyuanzaixian.xxx.indexable.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    private EditText editText;

    public static SecondFragment newInstance() {
        // Required empty public constructor
        return new SecondFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editText = (EditText) getActivity().findViewById(R.id.edit_delete);
        ImageButton delete_btn = (ImageButton) getActivity().findViewById(R.id.delete_btn);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText == null){
                    ALog.d("Nothing");
                }else {
                    editText.setText("");
                }
            }
        });
    }
}
