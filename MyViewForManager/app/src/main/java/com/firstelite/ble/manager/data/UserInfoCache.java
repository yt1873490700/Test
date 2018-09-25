package com.firstelite.ble.manager.data;

public class UserInfoCache  {
    public static UserInfoCache mUserInfoCache;
    public static UserInfoCache getInstance(){
       if(mUserInfoCache == null){
           mUserInfoCache = new UserInfoCache();
       }
        return mUserInfoCache;
    }
    private String USER_NAME = "user_name";
    private String USER_PHONE = "user_phone";
    private String USER_AGE = "user_age";
    private String USER_PSD = "user_psd";
    private String USER_SEX = "user_sex";
    private String USER_WORK = "user_work";
    private String USER_WORKPLACE = "user_workplace";
    private String SAVE_PSD = "save_psd";//1为记住密码

    public String getUSER_NAME() {
        return PreferencesUtils.getString(ContextUtil.getInstance().getApplicationContext(),USER_NAME,"");
    }

    public void setUSER_NAME(String user_name) {
        PreferencesUtils.putString(ContextUtil.getInstance().getApplicationContext(),USER_NAME,user_name);
    }

    public String getUSER_PHONE() {
        return PreferencesUtils.getString(ContextUtil.getInstance().getApplicationContext(),USER_PHONE,"");
    }

    public void setUSER_PHONE(String user_phone) {
        PreferencesUtils.putString(ContextUtil.getInstance().getApplicationContext(),USER_PHONE,user_phone);
    }

    public String getUSER_AGE() {
        return PreferencesUtils.getString(ContextUtil.getInstance().getApplicationContext(),USER_AGE,"");
    }

    public void setUSER_AGE(String user_age) {
        PreferencesUtils.putString(ContextUtil.getInstance().getApplicationContext(),USER_AGE,user_age);
    }

    public String getUSER_PSD() {
        return PreferencesUtils.getString(ContextUtil.getInstance().getApplicationContext(),USER_PSD,"");
    }

    public void setUSER_PSD(String user_psd) {
        PreferencesUtils.putString(ContextUtil.getInstance().getApplicationContext(),USER_PSD,user_psd);
    }

    public String getUSER_SEX() {
        return PreferencesUtils.getString(ContextUtil.getInstance().getApplicationContext(),USER_SEX,"");
    }

    public void setUSER_SEX(String user_sex) {
        PreferencesUtils.putString(ContextUtil.getInstance().getApplicationContext(),USER_SEX,user_sex);
    }

    public String getUSER_WORK() {
        return PreferencesUtils.getString(ContextUtil.getInstance().getApplicationContext(),USER_WORK,"");
    }

    public void setUSER_WORK(String user_work) {
        PreferencesUtils.putString(ContextUtil.getInstance().getApplicationContext(),USER_WORK,user_work);
    }

    public String getUSER_WORKPLACE() {
        return PreferencesUtils.getString(ContextUtil.getInstance().getApplicationContext(),USER_WORKPLACE,"");
    }

    public void setUSER_WORKPLACE(String user_workplace) {
        PreferencesUtils.putString(ContextUtil.getInstance().getApplicationContext(),USER_WORKPLACE,user_workplace);
    }

    public String getSAVE_PSD() {
        return PreferencesUtils.getString(ContextUtil.getInstance().getApplicationContext(),SAVE_PSD,"");
    }

    public void setSAVE_PSD(String save_psd) {
        PreferencesUtils.putString(ContextUtil.getInstance().getApplicationContext(),SAVE_PSD,save_psd);
    }
}
