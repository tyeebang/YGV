package com.example.ygv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class LogSignController {


    // 메인화면 닫기
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



    // 회원가입 팝업 및 팝업 닫기
    @FXML
    private Button signPopBut, signCloseBut;
    public void signUpPopup() {
        Stage mainStage = (Stage) signPopBut.getScene().getWindow();

        pop = new Stage(StageStyle.DECORATED);
        pop.initModality(Modality.WINDOW_MODAL);
        pop.initOwner(mainStage);

        try {
            Parent nextScene = FXMLLoader.load(getClass().getResource("signUp.fxml"));

            Scene scene = new Scene(nextScene);
            pop.setScene(scene);
            pop.setTitle("signUp");
            pop.setResizable(false);
            pop.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUpClose() {
        pop = (Stage) signCloseBut.getScene().getWindow();
        pop.close();
    }


    // 회원가입
    @FXML
    private TextField signId, signPw, signPwCheck, signName;
    @FXML
    private Button insertBut;
    public void insertMember() {
        DBUtil db = new DBUtil();
        Connection conn = db.getConnection();

        PreparedStatement pstmt = null;
        String sql = "INSERT INTO users(id, pw, name) VALUES(?,?,?)";

        String checkId = signId.getText();
        String checkPw = signPw.getText();
        String pwCheckText = signPwCheck.getText();
        String checkName = signName.getText();

        if (checkId.equals("") || checkPw.equals("") || checkName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고창");
            alert.setHeaderText("경고!");
            alert.setContentText("아이디, 비밀번호, 이름 중 공백인 란이 존재합니다.");
            alert.showAndWait();
        } else {
            try {
                if (checkPw.equals(pwCheckText)) {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, signId.getText());
                    pstmt.setString(2, signPw.getText());
                    pstmt.setString(3, signName.getText());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("회원가입");
                    alert.setHeaderText("회원가입 하시겠습니까?");
                    alert.setContentText("");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        pstmt.executeUpdate();

                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("회원가입");
                        alert1.setHeaderText("회원가입 완료");
                        alert1.setContentText("메인화면으로 돌아갑니다");

                        alert1.showAndWait();

                        signUpClose();
                    } else {
                        alert.close();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("경고창");
                    alert.setHeaderText("경고!");
                    alert.setContentText("비밀번호 입력란과 비밀번호 확인란의 내용이 일치 하지 않습니다.");

                    alert.showAndWait();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("경고창");
                alert.setHeaderText("경고!");
                alert.setContentText("아이디 중복이거나 아이디, 비밀번호, 이름의 길이가 초과됩니다.");

                alert.showAndWait();
            }
        }

    }



    // 로그인 화면으로 이동 및 돌아가기
    @FXML
    private Button goLoginBut, backMainBut;
    public void goLoginScene() {
        try {
            Parent nextScene = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(nextScene);

            Stage primaryStage = (Stage) goLoginBut.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backMainScene() {
        try {
            Parent nextScene = FXMLLoader.load(getClass().getResource("first.fxml"));
            Scene scene = new Scene(nextScene);

            Stage primaryStage = (Stage) backMainBut.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // 로그인
    @FXML
    private TextField logId, logPw;
    @FXML
    private Button loginBut;
    public String DBPw, scenePw;

    public void login() {
        DBUtil db = new DBUtil();
        Connection conn = db.getConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT pw FROM users WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, logId.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            rs = pstmt.executeQuery();
            rs.next();
            DBPw = rs.getString("pw");
            System.out.println("입력하신 아이디에 해당하는 패스워드 : " + DBPw);

            try {
                scenePw = logPw.getText();
                System.out.println("비밀번호 입력란에 입력하신 패스워드 : " + scenePw);

                if (scenePw.equals(DBPw)) {
                    System.out.println("입력하신 아이디와 비밀번호가 일치합니다");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("로그인");
                    alert.setHeaderText("정말 로그인 하시겠습니까?");
                    alert.setContentText("로그인 시 메인화면으로 이동합니다.");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        try {
                            Parent nextScene = FXMLLoader.load(getClass().getResource("main.fxml"));
                            Scene scene = new Scene(nextScene);

                            Stage primaryStage = (Stage) loginBut.getScene().getWindow();
                            primaryStage.setScene(scene);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        alert.close();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("경고창");
                    alert.setHeaderText("경고!");
                    alert.setContentText("입력하신 아이디와 비밀번호가 일치하지 않습니다");

                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고창");
            alert.setHeaderText("경고!");
            alert.setContentText("해당 아이디는 존재하지 않습니다.");

            alert.showAndWait();
        }
    }
}
