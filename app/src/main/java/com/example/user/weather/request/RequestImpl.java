//package com.example.user.weather.request;
//
//import com.google.gson.FieldNamingPolicy;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.internal.bind.DateTypeAdapter;
//import retrofit.GsonConverterFactory;
//import retrofit.Retrofit;
//import retrofit.RxJavaCallAdapterFactory;
//
//import javax.inject.Inject;
//import java.util.Date;
//
//public class RequestImpl implements Request{
//
//    private static final String END_POINT = "http://weather.livedoor.com";
//
//    @Inject
//    public RequestImpl(){
//    }
//
//    @Override
//    public Retrofit connection() {
//        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .registerTypeAdapter(Date.class, new DateTypeAdapter())
//                .create();
//
//        return  new Retrofit.Builder()
//                .baseUrl(END_POINT)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//    }
//}
