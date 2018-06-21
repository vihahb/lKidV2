package com.sproject.ikidz.sdk.Utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.sproject.ikidz.R;
import com.sproject.ikidz.iKidApplications;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by vivu on 11/27/17
 * xtel.vn
 */

public class RequestOkHttp {

    private static final String TAG = "RequestOkHttp";

    public RequestOkHttp() {
    }

    public void postApi(String url, String jsonObject, ResponseHandle responseHandle) {
        new PostToServer(responseHandle).execute(url, jsonObject);
    }

    public void postApi(String url, String jsonObject, String token, ResponseHandle responseHandle) {
        new PostToServerWithToken(responseHandle).execute(url, jsonObject, token);
    }

    public void getApi(String url, ResponseHandle responseHandle) {
        new GetToServer(responseHandle).execute(url);
    }

    public void getApi(String url, String token, ResponseHandle responseHandle) {
        new GetToServerWithToken(responseHandle).execute(url, token);
    }

    public void putApi(String url, String jsonObject, String session, ResponseHandle responseHandle) {
        new PutToServer(responseHandle).execute(url, jsonObject, session);
    }

    public void deleteApi(String url, String delete, String session, ResponseHandle responseHandle) {
        new DeleteToServer(responseHandle).execute(url, delete, session);
    }

    private class PostToServer extends AsyncTask<String, Integer, String> {
        private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private ResponseHandle responseHandle;
        private boolean isSuccess = true;

        PostToServer(ResponseHandle responseHandle) {
            this.responseHandle = responseHandle;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(params[0]);

                if (params[1] != null) {
                    RequestBody body = RequestBody.create(JSON, params[1]);
                    builder.post(body);
                }

                Request request = builder.build();

                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
                isSuccess = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isSuccess)
                responseHandle.onSuccess(s);
            else
                responseHandle.onError(new Exception("SYSTEM-EXCEPTION: " + iKidApplications.context.getString(R.string.message_can_not_request)));
        }
    }

    private class PostToServerWithToken extends AsyncTask<String, Integer, String> {
        private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private ResponseHandle responseHandle;
        private boolean isSuccess = true;

        PostToServerWithToken(ResponseHandle responseHandle) {
            this.responseHandle = responseHandle;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(params[0]);

                builder.addHeader("Content-Type", "application/json; charset=utf-8");

                if (params[1] != null) {
                    RequestBody body = RequestBody.create(JSON, params[1]);
                    builder.post(body);
                }

                if (params[2] != null) {
                    builder.addHeader("Authorization", "Bearer " + params[2]);
                }
                Request request = builder.build();

                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
                isSuccess = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isSuccess)
                responseHandle.onSuccess(s);
            else
                responseHandle.onError(new Exception("SYSTEM-EXCEPTION: " + iKidApplications.context.getString(R.string.message_can_not_request)));
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetToServer extends AsyncTask<String, Integer, String> {
        private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private ResponseHandle responseHandle;
        private boolean isSuccess = true;

        GetToServer(ResponseHandle responseHandle) {
            this.responseHandle = responseHandle;
        }

        @Override
        protected void onPreExecute() {
            isSuccess = true;
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(params[0]);

                Request request = builder.build();

                Log.e(TAG, "GET - Request: " + request.toString() + " #### ");

                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                isSuccess = false;
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isSuccess)
                responseHandle.onSuccess(s);
            else
                responseHandle.onError(new Exception("SYSTEM-EXCEPTION: " + iKidApplications.context.getString(R.string.message_can_not_request)));
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetToServerWithToken extends AsyncTask<String, Integer, String> {
        private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private ResponseHandle responseHandle;
        private boolean isSuccess = true;

        GetToServerWithToken(ResponseHandle responseHandle) {
            this.responseHandle = responseHandle;
        }

        @Override
        protected void onPreExecute() {
            isSuccess = true;
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(params[0]);

                if (params[1] != null) {
                    builder.addHeader("Authorization", "Bearer " + params[1]);
                }

                Request request = builder.build();

                Log.e(TAG, "GET - Request: " + request.toString() + " #### ");

                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                isSuccess = false;
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isSuccess)
                responseHandle.onSuccess(s);
            else
                responseHandle.onError(new Exception("SYSTEM-EXCEPTION: " + iKidApplications.context.getString(R.string.message_can_not_request)));
        }
    }

    private class PutToServer extends AsyncTask<String, Integer, String> {
        private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private ResponseHandle responseHandle;
        private boolean isSuccess = true;

        PutToServer(ResponseHandle responseHandle) {
            this.responseHandle = responseHandle;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(params[0]);

                if (params[1] != null) {
                    RequestBody body = RequestBody.create(JSON, params[1]);
                    builder.put(body);
                }
                Request request = builder.build();

                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                isSuccess = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isSuccess)
                responseHandle.onSuccess(s);
            else
                responseHandle.onError(new Exception("SYSTEM-EXCEPTION: " + iKidApplications.context.getString(R.string.message_can_not_request)));
        }
    }

    private class DeleteToServer extends AsyncTask<String, Integer, String> {
        private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private ResponseHandle responseHandle;
        private boolean isSuccess = true;

        DeleteToServer(ResponseHandle responseHandle) {
            this.responseHandle = responseHandle;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(params[0]);

                if (params[1] != null) {
                    RequestBody body = RequestBody.create(JSON, params[1]);
                    builder.delete(body);
                }
                Request request = builder.build();

                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                isSuccess = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isSuccess)
                responseHandle.onSuccess(s);
            else
                responseHandle.onError(new Exception("SYSTEM-EXCEPTION: " + iKidApplications.context.getString(R.string.message_can_not_request)));
        }
    }
}