package com.jacken.javafx.lesson1;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        initWindow(primaryStage);

        primaryStage.show();
    }




    public  static  InputStream  getImage() throws Exception{
        //new一个URL对象
        URL url = new URL("http://img14.360buyimg.com/n1/jfs/t1/106554/33/6485/119186/5df33fd9E8d680f6a/9ee82b1ca59651e6.jpg");
        //打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        System.out.println(inStream);
        return inStream;
    }


    public  void  addListener(Stage primaryStage){
        //添加监听
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue.doubleValue());
            }
        });

        //添加监听
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue.doubleValue());
            }
        });

        //添加监听
        primaryStage.xProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue.doubleValue());
            }
        });

        //添加监听
        primaryStage.yProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue.doubleValue());
            }
        });


    }

    @Override
    public void init() throws Exception {
        System.out.println("init");
        super.init();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
        super.stop();
    }

    public  void  initWindow(Stage primaryStage) throws Exception{
//        HostServices hostServices = getHostServices();
//        hostServices.showDocument("www.baidu.com");
        primaryStage.setTitle("Jacken");
        primaryStage.setHeight(90);
        primaryStage.setWidth(250);
        Button button = new Button();
        button.setText("百度一下");
        button.setPrefHeight(50);
        button.setPrefWidth(250);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HostServices hostServices = getHostServices();
        hostServices.showDocument("www.baidu.com");
            }
        });
        Group group = new Group();
        group.getChildren().add(button);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        //添加监听
        addListener(primaryStage);
        //设置图标
        primaryStage.getIcons().add(new Image(getImage()));
        //设置全屏
//        primaryStage.setFullScreen(true);
//        primaryStage.setScene(new Scene(new Group()));
    }
}
