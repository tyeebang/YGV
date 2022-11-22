package com.example.ygv;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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

    // 박스오피스 순위
    public ListView dailyAllRank, dailyAllName, dailyAllDt, dailyAllCnt, dailyAllAcc,
            dailyDomesticRank, dailyDomesticName, dailyDomesticDt, dailyDomesticCnt, dailyDomesticAcc,
            dailyOverseasRank, dailyOverseasName, dailyOverseasDt, dailyOverseasCnt, dailyOverseasAcc,
            weeklyAllRank, weeklyAllName, weeklyAllDt, weeklyAllCnt, weeklyAllAcc,
            weeklyDomesticRank, weeklyDomesticName, weeklyDomesticDt, weeklyDomesticCnt, weeklyDomesticAcc,
            weeklyOverseasRank, weeklyOverseasName, weeklyOverseasDt, weeklyOverseasCnt, weeklyOverseasAcc;

    public void getDailyAll() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=20221120").get();

            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("dailyBoxOffice:nth-child(" + i + ")");

                for (Element dailyBoxOffice : locList) {
                    Element movieRank = dailyBoxOffice.selectFirst("rank");
                    Element movieName = dailyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = dailyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = dailyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = dailyBoxOffice.selectFirst("audiAcc");

                    dailyAllRank.getItems().add(movieRank.text() + "위");
                    dailyAllName.getItems().add(movieName.text());
                    dailyAllDt.getItems().add(movieOpenDay.text());
                    dailyAllCnt.getItems().add(movieTodayAudience.text() + "명");
                    dailyAllAcc.getItems().add(movieAccumulateAudience.text() + "명");
                }
            }
        } catch (Exception e) {
        }
    }
    public void getDailyDomestic() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=20221120&repNationCd=K").get();

            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("dailyBoxOffice:nth-child(" + i + ")");

                for (Element dailyBoxOffice : locList) {
                    Element movieRank = dailyBoxOffice.selectFirst("rank");
                    Element movieName = dailyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = dailyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = dailyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = dailyBoxOffice.selectFirst("audiAcc");

                    dailyDomesticRank.getItems().add(movieRank.text() + "위");
                    dailyDomesticName.getItems().add(movieName.text());
                    dailyDomesticDt.getItems().add(movieOpenDay.text());
                    dailyDomesticCnt.getItems().add(movieTodayAudience.text() + "명");
                    dailyDomesticAcc.getItems().add(movieAccumulateAudience.text() + "명");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getDailyOverseas() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=20221120&repNationCd=F").get();

            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("dailyBoxOffice:nth-child(" + i + ")");

                for (Element dailyBoxOffice : locList) {
                    Element movieRank = dailyBoxOffice.selectFirst("rank");
                    Element movieName = dailyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = dailyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = dailyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = dailyBoxOffice.selectFirst("audiAcc");

                    dailyOverseasRank.getItems().add(movieRank.text() + "위");
                    dailyOverseasName.getItems().add(movieName.text());
                    dailyOverseasDt.getItems().add(movieOpenDay.text());
                    dailyOverseasCnt.getItems().add(movieTodayAudience.text() + "명");
                    dailyOverseasAcc.getItems().add(movieAccumulateAudience.text() + "명");
                }
            }
        } catch (Exception e) {
        }
    }
    public void getWeeklyAll() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchWeeklyBoxOfficeList.xml?key=b4daf89276d2e536834f8d871eb6f9ab&targetDt=20221120&weekGb=0").get();

            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("weeklyBoxOffice:nth-child(" + i + ")");

                for (Element weeklyBoxOffice : locList) {
                    Element movieRank = weeklyBoxOffice.selectFirst("rank");
                    Element movieName = weeklyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = weeklyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = weeklyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = weeklyBoxOffice.selectFirst("audiAcc");

                    weeklyAllRank.getItems().add(movieRank.text() + "위");
                    weeklyAllName.getItems().add(movieName.text());
                    weeklyAllDt.getItems().add(movieOpenDay.text());
                    weeklyAllCnt.getItems().add(movieTodayAudience.text() + "명");
                    weeklyAllAcc.getItems().add(movieAccumulateAudience.text() + "명");
                }
            }
        } catch (Exception e) {
        }
    }
    public void getWeeklyDomestic() {
        try {
            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchWeeklyBoxOfficeList.xml?key=b4daf89276d2e536834f8d871eb6f9ab&targetDt=20221120&weekGb=0&repNationCd=K").get();

            for (int i = 1; i <= 10; i++) {
                Elements locList = doc.select("weeklyBoxOffice:nth-child(" + i + ")");

                for (Element weeklyBoxOffice : locList) {
                    Element movieRank = weeklyBoxOffice.selectFirst("rank");
                    Element movieName = weeklyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = weeklyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = weeklyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = weeklyBoxOffice.selectFirst("audiAcc");

                    weeklyDomesticRank.getItems().add(movieRank.text() + "위");
                    weeklyDomesticName.getItems().add(movieName.text());
                    weeklyDomesticDt.getItems().add(movieOpenDay.text());
                    weeklyDomesticCnt.getItems().add(movieTodayAudience.text() + "명");
                    weeklyDomesticAcc.getItems().add(movieAccumulateAudience.text() + "명");
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
                Elements locList = doc.select("weeklyBoxOffice:nth-child(" + i + ")");

                for (Element weeklyBoxOffice : locList) {
                    Element movieRank = weeklyBoxOffice.selectFirst("rank");
                    Element movieName = weeklyBoxOffice.selectFirst("movieNm");
                    Element movieOpenDay = weeklyBoxOffice.selectFirst("openDt");
                    Element movieTodayAudience = weeklyBoxOffice.selectFirst("audiCnt");
                    Element movieAccumulateAudience = weeklyBoxOffice.selectFirst("audiAcc");

                    weeklyOverseasRank.getItems().add(movieRank.text() + "위");
                    weeklyOverseasName.getItems().add(movieName.text());
                    weeklyOverseasDt.getItems().add(movieOpenDay.text());
                    weeklyOverseasCnt.getItems().add(movieTodayAudience.text() + "명");
                    weeklyOverseasAcc.getItems().add(movieAccumulateAudience.text() + "명");
                }
            }
        } catch (Exception e) {
        }
    }

    // 검색 기능(영화명과 감독명만 사용 가능)
}