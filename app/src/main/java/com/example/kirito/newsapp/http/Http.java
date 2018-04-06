package com.example.kirito.newsapp.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by kirito on 2018.04.03.
 */

public class Http {
    public static String NEWSLIST_AMUSE = "http://120.76.205.241:8000/news/qihoo?kw=%E5%A8%B1%E4%B9%90&site=toutiao.com&" +
            "apikey=eCbKpTUmEFqOxWznHTr4uPmK6ER0DbsjQYCvYyXEICsUVscWogfVNIpKwOdd2n2w";
    public static String NEWSLIST_AMRY = "http://120.76.205.241:8000/news/qihoo?kw=%E5%86%9B%E4%BA%8B&site=toutiao.com&" +
            "apikey=eCbKpTUmEFqOxWznHTr4uPmK6ER0DbsjQYCvYyXEICsUVscWogfVNIpKwOdd2n2w";
    public static String NEWSLIST_GYM = "http://120.76.205.241:8000/news/qihoo?kw=%E4%BD%93%E8%82%B2&site=toutiao.com&" +
            "apikey=eCbKpTUmEFqOxWznHTr4uPmK6ER0DbsjQYCvYyXEICsUVscWogfVNIpKwOdd2n2w";
    public static String NEWSLIST_ECONOMY = "http://120.76.205.241:8000/news/qihoo?kw=%E8%B4%A2%E7%BB%8F&site=toutiao.com&" +
            "apikey=eCbKpTUmEFqOxWznHTr4uPmK6ER0DbsjQYCvYyXEICsUVscWogfVNIpKwOdd2n2w";
    public static String NEWSLIST_HISTORY = "http://120.76.205.241:8000/news/qihoo?kw=%E5%8E%86%E5%8F%B2&site=toutiao.com&" +
            "apikey=eCbKpTUmEFqOxWznHTr4uPmK6ER0DbsjQYCvYyXEICsUVscWogfVNIpKwOdd2n2w";
    public static String NEWSLIST_CAR = "http://120.76.205.241:8000/news/qihoo?kw=%E6%B1%BD%E8%BD%A6&site=toutiao.com&" +
            "apikey=eCbKpTUmEFqOxWznHTr4uPmK6ER0DbsjQYCvYyXEICsUVscWogfVNIpKwOdd2n2w";
    public static String NEWSLIST_FASHION = "http://120.76.205.241:8000/news/qihoo?kw=%E6%97%B6%E5%B0%9A&site=toutiao.com&" +
            "apikey=eCbKpTUmEFqOxWznHTr4uPmK6ER0DbsjQYCvYyXEICsUVscWogfVNIpKwOdd2n2w";

    private static final String TAG = "Http";

    public static String get(String urlAddr){
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlAddr);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //Log.e(TAG, "get: response---"+response.toString() );
                return response.toString();
            } else {
                throw new IOException("Network Error - response code: " + con.getResponseCode());
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    public static String getNewsList(int id){
        String data = null;
        switch (id){
            case 1:
                data =  get(NEWSLIST_AMUSE);
                break;
            case 2:
                data = get(NEWSLIST_AMRY);
                break;
            case 3:
                data = get(NEWSLIST_GYM);
                break;
            case 4:
                data = get(NEWSLIST_ECONOMY);
                break;
            case 5:
                data = get(NEWSLIST_HISTORY);
                break;
            case 6:
                data = get(NEWSLIST_CAR);
                break;
            case 7:
                data = get(NEWSLIST_FASHION);
                break;
            default:
            break;
        }
        return data;
    }
}
