package com.example.ygv;

import javafx.fxml.Initializable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        getDailyDomestic();
        getDailyOverseas();
        getDailyAll();
        getWeeklyDomestic();
        getWeeklyOverseas();
        getWeeklyAll();
    }

    public void getDailyDomestic() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=20221120&repNationCd=K").get();

            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("dailyBoxOffice:nth-child("+ i +")");

                for (Element dailyBoxOffice : locList) {
                    Element movieRank = dailyBoxOffice.selectFirst("rank");
                    Element movieName = dailyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = dailyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = dailyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = dailyBoxOffice.selectFirst("audiAcc");

                    System.out.println("일일 박스오피스 국내"
                            +"현재 영화의 순위 : " + movieRank.text()
                            + " | 영화의 이름 : " + movieName.text()
                            + " | 영화의 개봉일 : " + movieOpenDay.text()
                            + " | 영화의 당일 관객수 : " + movieTodayAudience.text()
                            + " | 영화의 누적 관객수 : " + movieAccumulateAudience.text()
                    );
                }
            }
        } catch (Exception e) {
        }
    }

    public void getDailyOverseas() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=20221120&repNationCd=F").get();

            System.out.println("");
            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("dailyBoxOffice:nth-child("+ i +")");

                for (Element dailyBoxOffice : locList) {
                    Element movieRank = dailyBoxOffice.selectFirst("rank");
                    Element movieName = dailyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = dailyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = dailyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = dailyBoxOffice.selectFirst("audiAcc");

                    System.out.println("일일 박스오피스 해외"
                            +"현재 영화의 순위 : " + movieRank.text()
                            + " | 영화의 이름 : " + movieName.text()
                            + " | 영화의 개봉일 : " + movieOpenDay.text()
                            + " | 영화의 당일 관객수 : " + movieTodayAudience.text()
                            + " | 영화의 누적 관객수 : " + movieAccumulateAudience.text()
                    );
                }
            }
        } catch (Exception e) {
        }
    }

    public void getDailyAll() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=20221120").get();

            System.out.println("");
            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("dailyBoxOffice:nth-child("+ i +")");

                for (Element dailyBoxOffice : locList) {
                    Element movieRank = dailyBoxOffice.selectFirst("rank");
                    Element movieName = dailyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = dailyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = dailyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = dailyBoxOffice.selectFirst("audiAcc");

                    System.out.println("일일 박스오피스 전체"
                            +"현재 영화의 순위 : " + movieRank.text()
                            + " | 영화의 이름 : " + movieName.text()
                            + " | 영화의 개봉일 : " + movieOpenDay.text()
                            + " | 영화의 당일 관객수 : " + movieTodayAudience.text()
                            + " | 영화의 누적 관객수 : " + movieAccumulateAudience.text()
                    );
                }
            }
        } catch (Exception e) {
        }
    }

    public void getWeeklyDomestic() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchWeeklyBoxOfficeList.xml?key=b4daf89276d2e536834f8d871eb6f9ab&targetDt=20221120&weekGb=0&repNationCd=K").get();

            System.out.println("");
            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("weeklyBoxOffice:nth-child("+ i +")");

                for (Element weeklyBoxOffice : locList) {
                    Element movieRank = weeklyBoxOffice.selectFirst("rank");
                    Element movieName = weeklyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = weeklyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = weeklyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = weeklyBoxOffice.selectFirst("audiAcc");

                    System.out.println("주간 박스오피스 국내"
                            +"현재 영화의 순위 : " + movieRank.text()
                            + " | 영화의 이름 : " + movieName.text()
                            + " | 영화의 개봉일 : " + movieOpenDay.text()
                            + " | 영화의 당일 관객수 : " + movieTodayAudience.text()
                            + " | 영화의 누적 관객수 : " + movieAccumulateAudience.text()
                    );
                }
            }
        } catch (Exception e) {
        }
    }

    public void getWeeklyOverseas() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchWeeklyBoxOfficeList.xml?key=b4daf89276d2e536834f8d871eb6f9ab&targetDt=20221120&weekGb=0&repNationCd=F").get();

            System.out.println("");
            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("weeklyBoxOffice:nth-child("+ i +")");

                for (Element weeklyBoxOffice : locList) {
                    Element movieRank = weeklyBoxOffice.selectFirst("rank");
                    Element movieName = weeklyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = weeklyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = weeklyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = weeklyBoxOffice.selectFirst("audiAcc");

                    System.out.println("주간 박스오피스 해외"
                            +"현재 영화의 순위 : " + movieRank.text()
                            + " | 영화의 이름 : " + movieName.text()
                            + " | 영화의 개봉일 : " + movieOpenDay.text()
                            + " | 영화의 당일 관객수 : " + movieTodayAudience.text()
                            + " | 영화의 누적 관객수 : " + movieAccumulateAudience.text()
                    );
                }
            }
        } catch (Exception e) {
        }
    }

    public void getWeeklyAll() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchWeeklyBoxOfficeList.xml?key=b4daf89276d2e536834f8d871eb6f9ab&targetDt=20221120&weekGb=0").get();

            System.out.println("");
            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("weeklyBoxOffice:nth-child("+ i +")");

                for (Element weeklyBoxOffice : locList) {
                    Element movieRank = weeklyBoxOffice.selectFirst("rank");
                    Element movieName = weeklyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = weeklyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = weeklyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = weeklyBoxOffice.selectFirst("audiAcc");

                    System.out.println("주간 박스오피스 전체"
                            +"현재 영화의 순위 : " + movieRank.text()
                            + " | 영화의 이름 : " + movieName.text()
                            + " | 영화의 개봉일 : " + movieOpenDay.text()
                            + " | 영화의 당일 관객수 : " + movieTodayAudience.text()
                            + " | 영화의 누적 관객수 : " + movieAccumulateAudience.text()
                    );
                }
            }
        } catch (Exception e) {
        }
    }
}