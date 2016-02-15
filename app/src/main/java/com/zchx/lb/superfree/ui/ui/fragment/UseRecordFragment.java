package com.zchx.lb.superfree.ui.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.callback.AccessAndUseRecordCallBack;
import com.zchx.lb.superfree.entity.AccessAndUseRecord;
import com.zchx.lb.superfree.entity.Record;
import com.zchx.lb.superfree.ui.ui.adapter.CommonAdapter;
import com.zchx.lb.superfree.ui.ui.adapter.ViewHolder;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 使用积分的记录
 */
public class UseRecordFragment extends Fragment {

    @Bind(R.id.id_ListView)
    ListView mListView;
    private String mobile;
    private List<Record> mDatas;
    private CommonAdapter<Record> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mobile= PreferencesStore.getInstance(getContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        initEvent();

    }

    private void initEvent() {
        //TODO  记得把mobile替换掉
        if (mobile != null) {
            OkHttpUtils.post()
                    .url(AppConstants.RequestPath.RECORDLESS)
                    .addParams(AppConstants.ParamDefaultValue.MOBILE, mobile)
                    .build().execute(new AccessAndUseRecordCallBack() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(AccessAndUseRecord accessAndUseRecord) {
                    mDatas = accessAndUseRecord.getResult_msg();
                    if (mDatas != null) {
                        initListView(mDatas);
                    }

                }
            });
        }

    }

    private void initListView(List<Record> mDatas) {
        mAdapter = new CommonAdapter<Record>(getActivity(), mDatas, R.layout.item_access_use_record) {
            @Override
            public void convert(ViewHolder holder, Record record) {

                holder.setText(R.id.tv_add_time, record.getAddtime());
                holder.setText(R.id.tv_mark, record.getMark());
                holder.setText(R.id.tv_user_integration, record.getUser_integration() + "");
            }
        };
        mListView.setAdapter(mAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_use_record, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
