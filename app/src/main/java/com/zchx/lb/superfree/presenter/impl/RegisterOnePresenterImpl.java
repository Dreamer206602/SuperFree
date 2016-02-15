package com.zchx.lb.superfree.presenter.impl;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.zchx.lb.superfree.model.RegisterOneModel;
import com.zchx.lb.superfree.presenter.RegisterOnePresenter;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.RegisterOneView;


import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.support.okhttp.callback.ResultCallback;
public class RegisterOnePresenterImpl implements RegisterOnePresenter {

    private RegisterOneView view;
    private RegisterOneModel model;

    public RegisterOnePresenterImpl(RegisterOneView view) {
        this.view = view;
        model = new RegisterOneModel();
    }

    @Override
    public void RegisterOne(final String mobile, String password, final String rePassword, final String recPrsonMobile) {

        model.RegisterOne(mobile, password, rePassword, recPrsonMobile, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                L.d("response---------------->"+response);
                try {
                    JSONObject object=new JSONObject(response);
                    String result = object.getString("result");
                    String result_msg = object.getString("result_msg");
                    if(result.equals("1")){
                        view.showRegisterOneSuccess(result_msg);
                    }else{
                        view.showRegisterOneErrorMsg(result_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //                if(object.get("result").toString().equals("1")){
//                    view.showRegisterOneSuccess();
//                }else{
//                    view.showRegisterOneErrorMsg(object.get("result_msg").toString());
//                }
            }
        });

    }


//    @Override
//    public void autoLogin(String mobile, String pwd) {

//        new LoginModel().login(mobile, pwd, new ResultCallback<JsonElement>() {
//                        @Override
//                        public void onError(Request request, Exception e) {
//                            view.showMsg("自动登录失败");
//                        }
//
//                        @Override
//                        public void onResponse(OauthUserEntity response) {
//                            App.getInstance().setOauth(response);
//                            view.showMsg("自动登录成功");
//                        }
//                    });

    }

    //    @Override
    //    public void signUp(String verify_code, final String phone, final String pwd) {
    //        model.RegisterOne(phone, pwd, verify_code, new ResultCallback<JsonElement>() {
    //            @Override
    //            public void onError(Request request, Exception e) {
    //                view.showSignUpError(e.getMessage());
    //            }
    //
    //            @Override
    //            public void onResponse(JsonElement response) {
    //                view.signUpSuccess();
    //                autoLogin(phone, pwd);
    //            }
    //        });
    //    }
    //
    //    @Override
    //    public void getVerifySMS(String phone, String pwd) {
    //
    //        model.getVerifySMS(phone, pwd, new ResultCallback<JsonElement>() {
    //            @Override
    //            public void onError(Request request, Exception e) {
    //                view.showVerifyError(e.getMessage());
    //            }
    //
    //            @Override
    //            public void onResponse(JsonElement response) {
    //                if (response.getAsBoolean()) {
    //                    view.showVerifySuccerss();
    //
    //
    //                }
    //            }
    //        });
    //    }
    //
    //    public void autoLogin(String phone, String pwd) {
    //        new LoginModel().login(phone, pwd, new ResultCallback<OauthUserEntity>() {
    //            @Override
    //            public void onError(Request request, Exception e) {
    //                view.showMsg("自动登录失败");
    //            }
    //
    //            @Override
    //            public void onResponse(OauthUserEntity response) {
    //                App.getInstance().setOauth(response);
    //                view.showMsg("自动登录成功");
    //            }
    //        });
    //
    //    }
//}
