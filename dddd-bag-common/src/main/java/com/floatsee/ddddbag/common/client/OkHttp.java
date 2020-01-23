package com.floatsee.ddddbag.common.client;

import okhttp3.*;
import okhttp3.OkHttpClient.Builder;

import java.io.IOException;

public class OkHttp {

    OkHttpClient.Builder builder;
    OkHttpClient okHttpClient = builder.build();
    public static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml; charset=utf-8");
    okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
        Request.Builder builder = new Request.Builder();
        builder.post(body);
        builder.url(url);
        Request request = builder.build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

    public static void main(String[] args) throws IOException {
        OkHttp example = new OkHttp();
        String json = example.bowlingJson("Jesse", "Jake");
        String response = example.post("http://www.roundsapp.com/post", json);
        System.out.println(response);

//        String response = example.run("https://cip.cc");
//        System.out.println(response);
    }
}

