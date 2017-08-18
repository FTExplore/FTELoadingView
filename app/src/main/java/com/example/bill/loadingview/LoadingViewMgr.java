package com.example.bill.loadingview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Administrator on 2016/9/2.
 */
public class LoadingViewMgr {

    public static final int SHOW_EMPT = 0;
    public static final int SHOW_LOADING = 1;
    public static final int SHOW_FAILT = 2;
    public static final int SHOW_BROTHER = 3;
    public static final int SHOW_HIDE = 4;
    public static final int SHOW_CUSTOM = 5;
    public static final int SHOW_FAILT_SERVER = 6;
    public static final int SHOW_STANDARD_CUNTOM = 7;//标准的自定义布局：一个图片，一个文字，图片及文字内容自定义

    private Context mContext;

    private View mLoadingEmptView;
    private View mLoadingStandardCustomView;
    private View mLoadingView;
    private View mLoadingFailt;
    private View mBrotherView;
    private ViewGroup mParentView;
    private View mCustomView;
    private ImageView mIvStandardCustom;
    private TextView mTvStandardCustom;
    private TextView mTvStandardCustomBelow;
    private ImageView mIvFail;
    private TextView mTvFail;
    private TextView mTvFailBelow;

    private ILoadingRefresh mLoadingRefresh;

    private boolean mIsVisibleBrotherView = false;

    public LoadingViewMgr(Context context, @NonNull ViewGroup parentView, @NonNull View brotherView) {
        mContext = context;
        mLoadingEmptView = LayoutInflater.from(context).inflate(R.layout.empty_view, null);
        mLoadingStandardCustomView = LayoutInflater.from(context).inflate(R.layout.loading_standard_custom_view, null);
        mLoadingView = LayoutInflater.from(context).inflate(R.layout.loading_view, null);
        mLoadingFailt = LayoutInflater.from(context).inflate(R.layout.loading_failt_view, null);
        /*mTvFail = (TextView) mLoadingFailt.findViewById(R.id.loading_fail_tv);
        mTvFailBelow = (TextView) mLoadingFailt.findViewById(R.id.loading_fail_tv_below);
        mIvFail = (ImageView) mLoadingFailt.findViewById(R.id.loading_fail_iv);
        mIvStandardCustom = (ImageView) mLoadingStandardCustomView.findViewById(R.id.standard_custom_image);
        mTvStandardCustom = (TextView) mLoadingStandardCustomView.findViewById(R.id.standard_custom_text);
        mTvStandardCustomBelow = (TextView) mLoadingStandardCustomView.findViewById(R.id.standard_custom_text_below);*/

        mParentView = parentView;
        mParentView.addView(mLoadingEmptView);
        mParentView.addView(mLoadingView);
        mParentView.addView(mLoadingFailt);
        mParentView.addView(mLoadingStandardCustomView);
        mBrotherView = brotherView;

        mLoadingFailt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadingRefresh != null) {
                    mLoadingRefresh.LoadingRefresh();
                }
            }
        });
    }

    public void setCustomView(@NonNull View customView) {
        mCustomView = customView;
        mParentView.addView(customView);
        mCustomView.setVisibility(View.GONE);
    }

    public void showStandardCustomView(int drawable, int text1,int text2) {
        String tex = mContext.getResources().getString(text1);
        String tex2 = mContext.getResources().getString(text2);
        showStandardCustomView(drawable, tex,tex2);
    }

    public void showStandardCustomView(int drawable, String text, String text2) {
        switchShow(SHOW_STANDARD_CUNTOM);
        mIvStandardCustom.setImageResource(drawable);

        mTvStandardCustom.setText(text);
        mTvStandardCustomBelow.setText(text2);
    }

    public void showStandardCustomView(int drawable, Spanned text) {
        switchShow(SHOW_STANDARD_CUNTOM);
        mIvStandardCustom.setImageResource(drawable);
        mTvStandardCustom.setTextSize(14);
        //mTvStandardCustom.setTextColor(mContext.getResources().getColor(R.color.colorTextApp0));
        mTvStandardCustom.setText(text);
    }

    public void setStandardCustomTextPadding(int left,int top,int right,int bottom){
        mTvStandardCustom.setPadding(left, top, right, bottom);
    }

    public void switchShow(int showType) {
        switch (showType) {
            case SHOW_EMPT: {
                mLoadingEmptView.setVisibility(View.VISIBLE);
                mLoadingView.setVisibility(View.GONE);
                mLoadingFailt.setVisibility(View.GONE);
                mParentView.setVisibility(View.VISIBLE);
                mLoadingStandardCustomView.setVisibility(View.GONE);
                if (mBrotherView != null) {
                    mBrotherView.setVisibility(mIsVisibleBrotherView ? View.VISIBLE : View.GONE);
                }
                if (mCustomView != null) {
                    mCustomView.setVisibility(View.GONE);
                }
            }
            break;
            case SHOW_STANDARD_CUNTOM:
                mLoadingEmptView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadingFailt.setVisibility(View.GONE);
                mParentView.setVisibility(View.VISIBLE);
                mLoadingStandardCustomView.setVisibility(View.VISIBLE);
                if (mBrotherView != null) {
                    mBrotherView.setVisibility(mIsVisibleBrotherView ? View.VISIBLE : View.GONE);
                }
                if (mCustomView != null) {
                    mCustomView.setVisibility(View.GONE);
                }
                break;
            case SHOW_LOADING: {
                mLoadingEmptView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.VISIBLE);
                mLoadingFailt.setVisibility(View.GONE);
                mParentView.setVisibility(View.VISIBLE);
                mLoadingStandardCustomView.setVisibility(View.GONE);
                if (mBrotherView != null) {
                    mBrotherView.setVisibility(mIsVisibleBrotherView ? View.VISIBLE : View.GONE);
                }
                if (mCustomView != null) {
                    mCustomView.setVisibility(View.GONE);
                }
            }
            break;
            case SHOW_FAILT: {
                mLoadingEmptView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadingFailt.setVisibility(View.VISIBLE);
                //mTvFail.setText(mContext.getString(R.string.yf_common_err_net_failt));
                //mIvFail.setImageResource(R.drawable.net_empty);
                mLoadingStandardCustomView.setVisibility(View.GONE);
                mParentView.setVisibility(View.VISIBLE);
                if (mBrotherView != null) {
                    mBrotherView.setVisibility(mIsVisibleBrotherView ? View.VISIBLE : View.GONE);
                }
                if (mCustomView != null) {
                    mCustomView.setVisibility(View.GONE);
                }
            }
            break;
            case SHOW_FAILT_SERVER: {
                mLoadingEmptView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadingFailt.setVisibility(View.VISIBLE);
                /*mTvFail.setText(mContext.getString(R.string.yf_common_err_server_error));
                mTvFailBelow.setText(mContext.getString(R.string.yf_common_err_server_error1));
                mIvFail.setImageResource(R.drawable.server_empty);*/
                mLoadingStandardCustomView.setVisibility(View.GONE);
                mParentView.setVisibility(View.VISIBLE);
                if (mBrotherView != null) {
                    mBrotherView.setVisibility(mIsVisibleBrotherView ? View.VISIBLE : View.GONE);
                }
                if (mCustomView != null) {
                    mCustomView.setVisibility(View.GONE);
                }
            }
            break;
            case SHOW_BROTHER: {
                mLoadingEmptView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadingFailt.setVisibility(View.GONE);
                mLoadingStandardCustomView.setVisibility(View.GONE);
                mParentView.setVisibility(View.VISIBLE);
                if (mBrotherView != null) {
                    mBrotherView.setVisibility(View.VISIBLE);
                }
                if (mCustomView != null) {
                    mCustomView.setVisibility(View.GONE);
                }
            }
            break;
            case SHOW_HIDE: {
                mLoadingEmptView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadingFailt.setVisibility(View.GONE);
                mParentView.setVisibility(View.GONE);
                mLoadingStandardCustomView.setVisibility(View.GONE);
                if (mBrotherView != null) {
                    mBrotherView.setVisibility(mIsVisibleBrotherView ? View.VISIBLE : View.GONE);
                }
                if (mCustomView != null) {
                    mCustomView.setVisibility(View.GONE);
                }
            }
            break;
            case SHOW_CUSTOM: {
                if (mCustomView != null) {
                    mCustomView.setVisibility(View.VISIBLE);
                }

                mLoadingEmptView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadingFailt.setVisibility(View.GONE);
                mLoadingStandardCustomView.setVisibility(View.GONE);
                mParentView.setVisibility(View.VISIBLE);
                if (mBrotherView != null) {
                    mBrotherView.setVisibility(mIsVisibleBrotherView ? View.VISIBLE : View.GONE);
                }
            }
            break;
        }
    }


    public void setRefreshCallback(ILoadingRefresh refresh) {
        mLoadingRefresh = refresh;
    }

    /*public void setTopHide(int dp) {
        //int pxTop = DeviceUtils.dipToPX(mContext, dp);
        ViewGroup.MarginLayoutParams layoutParamsEmpty = (ViewGroup.MarginLayoutParams) mLoadingEmptView.getLayoutParams();
        layoutParamsEmpty.setMargins(0, pxTop, 0, 0);
        ViewGroup.MarginLayoutParams layoutParamsFailt = (ViewGroup.MarginLayoutParams) mLoadingFailt.getLayoutParams();
        layoutParamsFailt.setMargins(0, pxTop, 0, 0);
    }*/

    public void setBackgroundColor(int color) {
        mLoadingEmptView.setBackgroundColor(color);
        mLoadingView.setBackgroundColor(color);
        mLoadingFailt.setBackgroundColor(color);
    }

    public void setVisibleBrotherView(boolean bVisible) {
        mIsVisibleBrotherView = bVisible;
    }

    public interface ILoadingRefresh {
        void LoadingRefresh();
    }
}
