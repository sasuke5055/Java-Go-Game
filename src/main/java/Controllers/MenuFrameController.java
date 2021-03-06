package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * Odpowiada za obslugę menu gry
 *
 * */
public class MenuFrameController {
    private Stage stage;

    @FXML
    private Button btnOnePlayer;

    @FXML
    private Button btnTwoPlayers;

    @FXML
    void btnOnePlayerOnAction(ActionEvent event) {
        startGame(0);
    }

    @FXML
    void btnTwoPlayersOnAction(ActionEvent event) {
        startGame(1);
    }


    public void startGame(int type){  //0 - singleplayer, 1- multiplayer


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("WaitingFrame.fxml"));
            WaitingFrameController wfc;

            if(type==0)
                wfc = new WaitingFrameController(true);
            else if(type==1)
                wfc = new WaitingFrameController();
            else return;

            loader.setController(wfc);
            Scene sceneG = new Scene(loader.load());
            stage.setTitle("Waiting");
            stage.setScene(sceneG);
            stage.setX(stage.getX()-100);
            wfc.setStage(stage);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }



    }

    public void setStage(Stage stage) {
        this.stage = stage;

    }
}