package com.example.ygv;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class MainController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        getDailyDomestic();
        getDailyOverseas();
        getDailyAll();
        getWeeklyDomestic();
        getWeeklyOverseas();
        getWeeklyAll();
        loadingTicket();
        loadingMyTicket();
    }

    // 일일 전체 박스오피스
    @FXML
    private ListView dailyAllRank, dailyAllName, dailyAllDt, dailyAllCnt, dailyAllAcc;

    public void getDailyAll() {
        try {
            Date dDate = new Date();
            dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -1));
            SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String yesterday = dSdf.format(dDate);

            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=" + yesterday).get();
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
            e.printStackTrace();
        }
    }

    // 일일 국내 박스오피스
    @FXML
    private ListView dailyDomesticRank, dailyDomesticName, dailyDomesticDt, dailyDomesticCnt, dailyDomesticAcc;

    public void getDailyDomestic() {
        try {
            Date dDate = new Date();
            dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -1));
            SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String yesterday = dSdf.format(dDate);

            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=" + yesterday + "&repNationCd=K").get();

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

    // 일일 해외 박스오피스
    @FXML
    private ListView dailyOverseasRank, dailyOverseasName, dailyOverseasDt, dailyOverseasCnt, dailyOverseasAcc;

    public void getDailyOverseas() {
        try {
            Date dDate = new Date();
            dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -1));
            SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String yesterday = dSdf.format(dDate);

            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchDailyBoxOfficeList.xml?key=2645c4c158a30cc3f6c9c33681d5122c&targetDt=" + yesterday + "&repNationCd=F").get();

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
            e.printStackTrace();
        }
    }

    // 주간 전체 박스오피스
    @FXML
    private ListView weeklyAllRank, weeklyAllName, weeklyAllDt, weeklyAllCnt, weeklyAllAcc;

    public void getWeeklyAll() {
        try {
            Date dDate = new Date();
            dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -7));
            SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String aWeekAgo = dSdf.format(dDate);

            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchWeeklyBoxOfficeList.xml?key=b4daf89276d2e536834f8d871eb6f9ab&targetDt=" + aWeekAgo + "&weekGb=0").get();

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
            e.printStackTrace();
        }
    }

    // 주간 국내 박스오피스
    @FXML
    private ListView weeklyDomesticRank, weeklyDomesticName, weeklyDomesticDt, weeklyDomesticCnt, weeklyDomesticAcc;

    public void getWeeklyDomestic() {
        try {
            Date dDate = new Date();
            dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -7));
            SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String aWeekAgo = dSdf.format(dDate);

            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchWeeklyBoxOfficeList.xml?key=b4daf89276d2e536834f8d871eb6f9ab&targetDt=" + aWeekAgo + "&weekGb=0&repNationCd=K").get();

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
            e.printStackTrace();
        }
    }

    // 주간 해외 박스오피스
    @FXML
    private ListView weeklyOverseasRank, weeklyOverseasName, weeklyOverseasDt, weeklyOverseasCnt, weeklyOverseasAcc;

    public void getWeeklyOverseas() {
        try {
            Date dDate = new Date();
            dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -7));
            SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String aWeekAgo = dSdf.format(dDate);

            Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" +
                    "searchWeeklyBoxOfficeList.xml?key=b4daf89276d2e536834f8d871eb6f9ab&targetDt=" + aWeekAgo + "&weekGb=0&repNationCd=F").get();

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
            e.printStackTrace();
        }
    }


    // 영화 검색
    public String movieNm, directorNm;

    @FXML
    private TextField movieNmText, directorNmText;

    @FXML
    private Button searchBut;

    @FXML
    private ListView searchName, searchOpenDt, searchRepNationNm, searchRepGenreNm, searchPeopleNm;

    public void searchMovie() {
        try {
            movieNm = movieNmText.getText();
            directorNm = directorNmText.getText();
            System.out.println(movieNm);
            System.out.println(directorNm);

            if (movieNm.equals("") && directorNm.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("경고창");
                alert.setHeaderText("경고!");
                alert.setContentText("오류, 영화명란과 감독명란이 모두 공백입니다.");
                alert.showAndWait();
            } else {
                searchName.getItems().clear();
                searchOpenDt.getItems().clear();
                searchRepNationNm.getItems().clear();
                searchRepGenreNm.getItems().clear();
                searchPeopleNm.getItems().clear();

                Document doc = Jsoup.connect("https://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.xml?" +
                        "key=f5eef3421c602c6cb7ea224104795888&movieNm=" + movieNm + "&directorNm=" + directorNm).get();

                for (int i = 1; i <= 10; i++) {
                    Elements locList = doc.select("movie:nth-child(" + i + ")");

                    for (Element movie : locList) {
                        Element movieName = movie.selectFirst("movieNm");
                        Element movieOpenDay = movie.selectFirst("openDt");
                        Element movieCountry = movie.selectFirst("repNationNm");
                        Element movieGenre = movie.selectFirst("repGenreNm");
                        Element movieDirector = movie.selectFirst("directors");

                        searchName.getItems().add(movieName.text());
                        searchOpenDt.getItems().add(movieOpenDay.text());
                        searchRepNationNm.getItems().add(movieCountry.text());
                        searchRepGenreNm.getItems().add(movieGenre.text());
                        searchPeopleNm.getItems().add(movieDirector.text());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 예매하기
    @FXML
    private ListView tickets, myTicketsTitle, myTicketsDate;
    @FXML
    private DatePicker picker;
    public String mTitle;
    public String date;

    public void loadingTicket() {
        try {
            String url = "http://www.cgv.co.kr/movies/?lt=1&ft=0";
            Document doc = Jsoup.connect(url).get();

            Elements locList = doc.select("div.sect-movie-chart");
            System.out.println(doc);
            System.out.println("============================================================");

            Iterator<Element> it = locList.select("strong.title").iterator();

            while (it.hasNext()) {
                System.out.println(it.next().text());
                tickets.getItems().add(it.next().text());
            }


        } catch (Exception e) {

        }
    }

    public void loadingMyTicket() {
        DBUtil db = new DBUtil();
        Connection conn = db.getConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM mytickets;";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            myTicketsTitle.getItems().clear();
            myTicketsDate.getItems().clear();
            while (rs.next()) {
                mTitle = rs.getString("title");
                date = rs.getString("date");
                myTicketsTitle.getItems().add(mTitle);
                myTicketsDate.getItems().add(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ticketing() {
        ObservableList ticket = tickets.getSelectionModel().getSelectedItems();
        LocalDate date = picker.getValue();
        System.out.println(ticket);
        System.out.println(date);

        DBUtil db = new DBUtil();
        Connection conn = db.getConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO mytickets(title, date) VALUES ('" + ticket + "','" + date + "');";
        System.out.println(ticket);

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            loadingMyTicket();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteTicket() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("티켓 판매");
        alert.setHeaderText("정말 판매하시겠습니까?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Object ticket = myTicketsTitle.getSelectionModel().getSelectedItem();
            System.out.println(ticket);

            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();

            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "DELETE FROM mytickets WHERE title='" + ticket + "';";

            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                loadingMyTicket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert.close();
        }
    }


    @FXML
    private Button mainCloseBtn;
    private Stage pop;

    public void mainClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("종료");
        alert.setHeaderText("정말 종료하시겠습니까?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            pop = (Stage) mainCloseBtn.getScene().getWindow();
            pop.close();
        } else {
            alert.close();
        }
    }
}