package c.iglesias.registrodedudas.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.iglesias.registrodedudas.R;
import c.iglesias.registrodedudas.Widget.SpacingDecoration;


public class BaseFragment extends Fragment {


    @BindView(R.id.id_txt_no_elementos)
    TextView txtNoElementos;

    @BindView(R.id.id_rv)
    RecyclerView rv;

    @BindView(R.id.id_fab_add)
    FloatingActionButton fab;

    @BindView(R.id.id_linear_progress)
    LinearLayout progress;

    @BindView(R.id.id_fl_info)
    FrameLayout frameLayoutInfo;


    RecyclerView.Adapter adapter;

    public BaseFragment() {

    }


    public static BaseFragment newInstance() {
        BaseFragment fragment = new BaseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new SpacingDecoration(8));
    }

    protected void showFab(boolean show) {
        fab.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    protected void showList(boolean show) {
        rv.setVisibility(show ? View.VISIBLE : View.GONE);
        txtNoElementos.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    protected void showInfo(boolean show){
        frameLayoutInfo.setVisibility(show?View.VISIBLE:View.GONE);
        progress.setVisibility(show?View.GONE:View.VISIBLE);
    }

    protected void setRvAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        rv.setAdapter(this.adapter);
    }

    protected RecyclerView.Adapter getAdapter() {
        return this.adapter;
    }

}
