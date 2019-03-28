package mvp.ujang.posmvp.module.struk.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.base.BaseFragment;

public class StrukFragment extends BaseFragment {

    public StrukFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_struk, null);

        findViews(view);
        initViews(view);
        initListeners(view);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void findViews(View view) {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void initListeners(View view) {

    }
}

